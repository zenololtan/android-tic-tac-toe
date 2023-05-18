package com.ztan.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ztan.tic_tac_toe.databinding.ActivityGameBinding
import com.ztan.tic_tac_toe.models.Board
import com.ztan.tic_tac_toe.models.BoardState
import com.ztan.tic_tac_toe.models.Cell

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private val vm: GameActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm.liveBoard.observe(this, onBoardChange)
        val mode: Int = intent.getIntExtra("mode", 0)
        bindButtons(mode)
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

    private fun bindButtons(mode: Int) = with(binding) {
        buttonHome.setOnClickListener {
            val intent = Intent(this@GameActivity, MainActivity::class.java)
            startActivity(intent)
        }
        buttonReset.setOnClickListener { vm.resetBoard() }
        cell0.setOnClickListener { vm.cellClicked(Cell.TOP_LEFT, mode) }
        cell1.setOnClickListener { vm.cellClicked(Cell.TOP_CENTER, mode) }
        cell2.setOnClickListener { vm.cellClicked(Cell.TOP_RIGHT, mode) }
        cell3.setOnClickListener { vm.cellClicked(Cell.CENTER_LEFT, mode) }
        cell4.setOnClickListener { vm.cellClicked(Cell.CENTER_CENTER, mode) }
        cell5.setOnClickListener { vm.cellClicked(Cell.CENTER_RIGHT, mode) }
        cell6.setOnClickListener { vm.cellClicked(Cell.BOTTOM_LEFT, mode) }
        cell7.setOnClickListener { vm.cellClicked(Cell.BOTTOM_CENTER, mode) }
        cell8.setOnClickListener { vm.cellClicked(Cell.BOTTOM_RIGHT, mode) }
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