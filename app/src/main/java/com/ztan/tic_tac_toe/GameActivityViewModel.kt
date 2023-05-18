package com.ztan.tic_tac_toe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ztan.tic_tac_toe.models.Board
import com.ztan.tic_tac_toe.models.BoardState
import com.ztan.tic_tac_toe.models.Cell
import com.ztan.tic_tac_toe.models.CellState

class GameActivityViewModel : ViewModel() {
    private var board: Board = Board()
    private var isX: Boolean = true
    val liveBoard = MutableLiveData(board)

    private fun updateBoard() {
        liveBoard.value = board
    }

    private fun aiTurn() {
        val circleWinningCell = board.findNextWinningMove(CellState.circle)
        val startWinningCell = board.findNextWinningMove(CellState.x)
        when {
            // If the AI can win, place a circle in that spot
            circleWinningCell != null -> board.setCell(circleWinningCell, CellState.circle)
            // If the AI is about to lose, place a circle in a blocking spot
            startWinningCell != null -> board.setCell(startWinningCell, CellState.circle)
            // Prioritize the middle
            board.setCell(Cell.CENTER_CENTER, CellState.circle) -> Unit
            // Otherwise place a circle in a random spot
            else -> do {
                val nextCell = Cell.values().random()
                val placeSuccess = board.setCell(nextCell, CellState.circle)
            } while (!placeSuccess)
        }
    }

    private fun singlePlayerClick(cell: Cell) {
        board.setCell(cell, CellState.x)
        updateBoard()
        if (board.boardState == BoardState.INCOMPLETE)
            aiTurn()
    }

    private fun multiplayerClick(cell: Cell) {
        if (isX) {
            board.setCell(cell, CellState.x)
            isX = false
        }
        else {
            board.setCell(cell, CellState.circle)
            isX = true
        }
    }

    fun cellClicked(cell: Cell, mode: Int) {
        if (board.boardState != BoardState.INCOMPLETE)
            return
        if (mode == 0)
            singlePlayerClick(cell)
        else
            multiplayerClick(cell)
        updateBoard()
    }

    fun resetBoard() {
        board.resetBoard()
        updateBoard()
    }
}