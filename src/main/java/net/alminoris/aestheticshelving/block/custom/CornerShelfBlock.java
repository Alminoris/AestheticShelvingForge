package net.alminoris.aestheticshelving.block.custom;

import com.mojang.serialization.MapCodec;
import net.alminoris.aestheticshelving.block.entity.ModBlockEntities;
import net.alminoris.aestheticshelving.block.entity.CornerShelfBlockEntity;
import net.alminoris.aestheticshelving.menu.CornerShelfMenu;
import net.alminoris.aestheticshelving.util.helper.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CornerShelfBlock extends BaseEntityBlock implements SimpleWaterloggedBlock
{
    private static final VoxelShape SHAPE = CornerShelfBlock.box(9, 7, 0, 16, 9, 7);
    private static final VoxelShape SHAPE1 = CornerShelfBlock.box(6, 7, 0, 16, 9, 4);
    private static final VoxelShape SHAPE2 = CornerShelfBlock.box(12, 7, 0, 16, 9, 10);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final MapCodec<CornerShelfBlock> CODEC = CornerShelfBlock.simpleCodec(CornerShelfBlock::new);

    public CornerShelfBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return getRotatedShape(state);
    }

    private VoxelShape getRotatedShape(BlockState state)
    {
        Direction direction = state.getValue(FACING);

        List<AABB> boxes = new ArrayList<>();
        boxes.add(SHAPE.bounds());
        boxes.add(SHAPE1.bounds());
        boxes.add(SHAPE2.bounds());

        return VoxelShapeHelper.rotateShape(boxes, direction);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new CornerShelfBlockEntity(pos, state);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        boolean waterlogged = ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection()).setValue(WATERLOGGED, waterlogged);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                     LevelAccessor world, BlockPos pos, BlockPos neighborPos)
    {
        if (state.getValue(WATERLOGGED))
        {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        Direction facing = state.getValue(FACING);
        if (direction == facing || direction == facing.getClockWise(Direction.Axis.Y))
        {
            BlockPos supportPos = pos.offset(facing.getNormal());
            BlockPos supportPos1 = pos.offset(facing.getClockWise(Direction.Axis.Y).getNormal());

            if (!world.getBlockState(supportPos).isSolidRender(world, supportPos) || !world.getBlockState(supportPos1).isSolidRender(world, supportPos1))
            {
                if (world instanceof Level realWorld)
                {
                    realWorld.destroyBlock(pos, true);
                }
                return Blocks.AIR.defaultBlockState();
            }
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved)
    {
        if (state.getBlock() != newState.getBlock())
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof CornerShelfBlockEntity cornerShelf)
            {
                Containers.dropContents(level, pos, cornerShelf);
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, moved);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        if (!world.isClientSide)
        {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (player instanceof ServerPlayer serverPlayer)
            {
                BlockPos poss = blockEntity.getBlockPos();
                serverPlayer.openMenu(new SimpleMenuProvider(
                        (containerId, inventory, pl) -> new CornerShelfMenu(containerId, inventory, (CornerShelfBlockEntity) blockEntity),
                        state.getBlock().getName()
                ), poss);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(type, ModBlockEntities.CORNER_SHELF_BLOCK_ENTITY.get(),
                (lvl, pos, st, be) -> be.tick(lvl, pos, st));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        Direction facing = state.getValue(FACING);
        BlockPos supportPos = pos.offset(facing.getNormal());
        BlockPos supportPos1 = pos.offset(facing.getClockWise(Direction.Axis.Y).getNormal());
        return world.getBlockState(supportPos).isSolidRender(world, supportPos) && world.getBlockState(supportPos1).isSolidRender(world, supportPos1);
    }
}