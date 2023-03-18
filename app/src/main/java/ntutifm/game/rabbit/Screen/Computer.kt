package ntutifm.game.rabbit.Screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.math.max
import kotlin.math.min

val arrD = arrayListOf(
    15,
    16,
    19,
    20,
    23,
    26,
    29,
    31,
    36,
    38,
    40,
    43,
    45,
    46,
    47,
    -1,
    10,
    11,
    12,
    -1,
    11,
    20,
    21,
    10,
    12,
    21,
    11,
    21,
    22,
    21,
    30,
    20,
    22,
    30,
    31,
    32,
    21,
    32,
    31,
    41,
    30,
    32,
    41,
    31,
    41,
    -1,
    -1,
    -1
)
val arrR = arrayListOf(
    15,
    16,
    17,
    18,
    22,
    26,
    30,
    33,
    41,
    44,
    48,
    52,
    56,
    57,
    60,
    -1,
    -1,
    -1,
    20,
    21,
    11,
    1,
    21,
    10,
    12,
    1,
    21,
    22,
    11,
    1,
    30,
    21,
    10,
    30,
    31,
    32,
    20,
    22,
    10,
    11,
    12,
    32,
    21,
    12,
    41,
    31,
    20,
    21,
    41,
    30,
    32,
    21,
    41,
    31,
    21,
    22,
    -1,
    30,
    31,
    32,
    -1
)

fun findMoveR(board: ArrayList<Int>): Int {
    var bestMove = 13 //初始化最先且最優的一步
    var bestScore = -9999 //初始化分數
    val rabbitRow = board.indexOf(2)
    Log.e("move", "1${arrR[rabbitRow]}")
    Log.e("move", "2${arrR[rabbitRow + 1]}") //57-58
    for (pos in arrR[rabbitRow] until arrR[rabbitRow + 1]) {
        val linkPoint = arrR[pos].div(10) * 3 + arrR[pos].mod(10)
        if (board[linkPoint] != 1) { //省略狗子
            board[linkPoint] = 2
            board[rabbitRow] = 0
            val score = minimax(isRabbit = false, board = board, depth = 5)
            Log.e("move", "$score")
            board[linkPoint] = 0 //還原
            board[rabbitRow] = 2
            if (score >= bestScore) {
                bestScore = score
                bestMove = linkPoint // 取最優
            }
        }
    }
    return bestMove


}

fun findMoveG(board: ArrayList<Int>): Int {
    var bestMove = 4 //初始化最先且最優的一步
    var bestScore = 9999 //初始化分數
    val dogs = arrayListOf(board.indexOfFirst { it == 1 },
        board.indexOfLast { it == 1 })
    //找出中間的狗子
    dogs.add(board[board.indexOfFirst { it != dogs[0] }])
    for (dog in dogs) { // 每隻狗子
        for (pos in arrR[dog] until arrR[dog + 1]) {
            val linkPoint = arrR[pos].div(10) * 3 + arrR[pos].mod(10)
            if (board[linkPoint] != 1 && board[linkPoint] != 2) { //省略狗子和兔子
                board[linkPoint] = 1
                board[dog] = 0
                val score = minimax(isRabbit = true, board = board, depth = 5)
                Log.e("move", "$score")
                board[linkPoint] = 0 //還原
                board[dog] = 1
                if (score <= bestScore) {
                    bestScore = score
                    bestMove = linkPoint // 取最優
                }
            }
        }
    }
    return bestMove


}

fun minimax(board: ArrayList<Int>, isRabbit: Boolean, depth: Int = 10): Int {
    val rabbit = board.indexOf(2)
    //先找出最先跟最後的狗子
    val dogs = arrayListOf(board.indexOfFirst { it == 1 },
        board.indexOfLast { it == 1 })
    //找出中間的狗子
    dogs.add(board[board.indexOfFirst { it != dogs[0] }])
    Log.e("cry", "$board")
    Log.e("cry2", "$depth")
    //depth == 0
    if (board[1] == 2 ||
        rabbit.div(3) < dogs[0].div(3) ||
        rabbit.div(3) < dogs[1].div(3) ||
        rabbit.div(3) < dogs[2].div(3) ||
        depth == 0
    ) {
        return 10
    } else {
        if (board[9] == 1 && board[10] == 1 && board[11] == 1) {
            return -10
        } else {
            if (isRabbit) { //兔子
                var bestScore = -99999 //初始化
                for (pos in arrR[rabbit] until arrR[rabbit + 1]) {
                    val linkPoint = arrR[pos].div(10) * 3 + arrR[pos].mod(10)
                    if (board[linkPoint] != 1) { //省略狗子
                        board[linkPoint] = 2
                        board[rabbit] = 0
                        val score = minimax(board, false, depth - 1)
                        board[linkPoint] = 0 //還原
                        board[rabbit] = 2
                        bestScore = max(bestScore, score)
                    }
                }
                return bestScore
            } else { //狗子
                var bestScore = 99999 //初始化

                for (dog in dogs) { // 每隻狗子
                    for (pos in arrD[dog] until arrD[dog + 1]) {
                        val linkPoint = arrD[pos].div(10) * 3 + arrD[pos].mod(10)
                        if (board[linkPoint] != 1 && board[linkPoint] != 2) { //省略狗子跟兔子
                            board[linkPoint] = 1
                            board[dog] = 0
                            val score = minimax(board, true, depth - 1)
                            board[linkPoint] = 0 //還原
                            board[dog] = 1
                            bestScore = min(bestScore, score) //取最優
                        }
                    }
                }
                return bestScore
            }
        }
    }
    return 0
}

@Composable
fun Computer(navController: NavController) {
    Box {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "選擇角色",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 200.dp)
            )
            ElevatedCard(modifier = Modifier
                .padding(top = 100.dp)
                .clickable { navController.navigate("PlayDog") }) {
                Text(text = "獵犬",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp, end = 20.dp, bottom = 15.dp)
                )
            }
            ElevatedCard(modifier = Modifier
                .padding(top = 100.dp)
                .clickable { navController.navigate("PlayRabbit") }) {
                Text(text = "兔子",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp, end = 20.dp, bottom = 15.dp)
                )
            }
        }
    }
}