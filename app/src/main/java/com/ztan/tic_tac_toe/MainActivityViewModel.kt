package com.ztan.tic_tac_toe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ztan.tic_tac_toe.models.Board
import com.ztan.tic_tac_toe.models.BoardState
import com.ztan.tic_tac_toe.models.Cell
import com.ztan.tic_tac_toe.models.CellState

class MainActivityViewModel : ViewModel() {
    private var board: Board = Board()
    val liveBoard = MutableLiveData(board)

    private fun updateBoard() {
        liveBoard.value = board
    }

    fun cellClicked(cell: Cell) {
        if (board.boardState != BoardState.INCOMPLETE)
            return
        board.setCell(cell, CellState.x)
        updateBoard()
    }

    fun resetBoard() {
        board.resetBoard()
        updateBoard()
    }
}