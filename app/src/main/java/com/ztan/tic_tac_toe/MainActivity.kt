package com.ztan.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ztan.tic_tac_toe.databinding.ActivityMainBinding
import com.ztan.tic_tac_toe.models.Board
import com.ztan.tic_tac_toe.models.BoardState
import com.ztan.tic_tac_toe.models.Cell

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val vm:MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm.liveBoard.observe(this, onBoardChange)
        bindButtons()
    }

    private val onBoardChange = Observer { board: Board ->
        updateBoardCell(board)
        updateGameStatus(board.boardState)
    }

    private fun updateBoardCell(board: Board) {
        binding.cell0.setImageResource(board.getState(Cell.TOP_LEFT).res)
        binding.cell1.setImageResource(board.getState(Cell.TOP_CENTER).res)
        binding.cell2.setImageResource(board.getState(Cell.TOP_RIGHT).res)
        binding.cell3.setImageResource(board.getState(Cell.CENTER_LEFT).res)
        binding.cell4.setImageResource(board.getState(Cell.CENTER_CENTER).res)
        binding.cell5.setImageResource(board.getState(Cell.CENTER_RIGHT).res)
        binding.cell6.setImageResource(board.getState(Cell.BOTTOM_LEFT).res)
        binding.cell7.setImageResource(board.getState(Cell.BOTTOM_CENTER).res)
        binding.cell8.setImageResource(board.getState(Cell.BOTTOM_RIGHT).res)
    }

    private fun bindButtons() = with(binding) {
        buttonReset.setOnClickListener { vm.resetBoard() }
        cell0.setOnClickListener { vm.cellClicked(Cell.TOP_LEFT) }
        cell1.setOnClickListener { vm.cellClicked(Cell.TOP_CENTER) }
        cell2.setOnClickListener { vm.cellClicked(Cell.TOP_RIGHT) }
        cell3.setOnClickListener { vm.cellClicked(Cell.CENTER_LEFT) }
        cell4.setOnClickListener { vm.cellClicked(Cell.CENTER_CENTER) }
        cell5.setOnClickListener { vm.cellClicked(Cell.CENTER_RIGHT) }
        cell6.setOnClickListener { vm.cellClicked(Cell.BOTTOM_LEFT) }
        cell7.setOnClickListener { vm.cellClicked(Cell.BOTTOM_CENTER) }
        cell8.setOnClickListener { vm.cellClicked(Cell.BOTTOM_RIGHT) }
    }

    private fun updateGameStatus(boardState: BoardState) = when(boardState) {
        BoardState.X_WIN -> {
            binding.textStatus.visibility = View.VISIBLE
            binding.textStatus.setText(R.string.message_x_win)
        }
        BoardState.CIRCLE_WIN -> {
            binding.textStatus.visibility = View.VISIBLE
            binding.textStatus.setText(R.string.message_circle_win)
        }
        BoardState.DRAW -> {
            binding.textStatus.visibility = View.VISIBLE
            binding.textStatus.setText(R.string.message_draw)
        }
        BoardState.INCOMPLETE -> {
            binding.textStatus.visibility = View.GONE
        }
    }
}