package com.ztan.tic_tac_toe.models

import androidx.annotation.DrawableRes
import com.ztan.tic_tac_toe.R

sealed class CellState(@DrawableRes val res: Int) {
    object x: CellState(R.drawable.ic_x)
    object circle: CellState(R.drawable.ic_circle)
    object empty: CellState(R.drawable.ic_empty)
}
