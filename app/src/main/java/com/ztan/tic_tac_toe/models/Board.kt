package com.ztan.tic_tac_toe.models

class Board(private val gameBoard:MutableMap<Cell, CellState> = mutableMapOf()) {
    fun getState(cell: Cell) : CellState {
        return gameBoard[cell] ?: CellState.empty
    }

    fun setCell(cell: Cell, state: CellState) : Boolean {
        if (gameBoard.containsKey(cell))
            return false
        gameBoard[cell] = state
        return true
    }

    fun resetBoard() {
        gameBoard.clear()
    }

    val boardState: BoardState get() = when {
        hasStateWon(CellState.x) -> BoardState.X_WIN
        hasStateWon(CellState.circle) -> BoardState.CIRCLE_WIN
        gameBoard.size < 9 -> BoardState.INCOMPLETE
        else -> BoardState.DRAW
    }

    private fun hasStateWon(state: CellState): Boolean {
        fun testState(vararg cells: Cell) = cells.all { cell ->
            gameBoard[cell] == state
        }

        return testState(Cell.TOP_LEFT, Cell.CENTER_LEFT, Cell.BOTTOM_LEFT) ||
                testState(Cell.TOP_CENTER, Cell.CENTER_CENTER, Cell.BOTTOM_CENTER) ||
                testState(Cell.TOP_RIGHT, Cell.CENTER_RIGHT, Cell.BOTTOM_RIGHT) ||
                testState(Cell.TOP_LEFT, Cell.TOP_CENTER, Cell.TOP_RIGHT) ||
                testState(Cell.CENTER_LEFT, Cell.CENTER_CENTER, Cell.CENTER_RIGHT) ||
                testState(Cell.BOTTOM_LEFT, Cell.BOTTOM_CENTER, Cell.BOTTOM_RIGHT) ||
                testState(Cell.TOP_LEFT, Cell.CENTER_CENTER, Cell.BOTTOM_RIGHT) ||
                testState(Cell.TOP_RIGHT, Cell.CENTER_CENTER, Cell.BOTTOM_LEFT)
    }
}