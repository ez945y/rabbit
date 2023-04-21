package ntutifm.game.rabbit.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ntutifm.game.rabbit.R
import ntutifm.game.rabbit.isStart
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
    var bestScore = 9999 //初始化分數
    val rabbitRow = board.indexOf(2)
    for (pos in arrR[rabbitRow] until arrR[rabbitRow + 1]) {
        if (arrR[pos] == -1){continue}
        val linkPoint = arrR[pos].div(10) * 3 + arrR[pos].mod(10)
        if (board[linkPoint] != 1) { //省略狗子
            board[linkPoint] = 2
            board[rabbitRow] = 0
            val score = minimax(Int.MAX_VALUE, Int.MIN_VALUE, isRabbit = false, board = board, depth = 10)
            board[linkPoint] = 0 //還原
            board[rabbitRow] = 2
            if (score <= bestScore) {
                bestScore = score
                bestMove = linkPoint // 取最優
            }
        }
    }
    return bestMove


}

fun findMoveD(board: ArrayList<Int>): ArrayList<Int> {
    var bestMove = 4 //初始化最先且最優的一步
    var bestScore = -9999 //初始化分數
    val dogs = arrayListOf<Int>()
    for ((i, item) in board.withIndex()){
        if (item == 1){
            dogs.add(i)
        }
    }
    var fromDog = dogs[0]
    for (dog in dogs) { // 每隻狗子
        for (pos in arrD[dog] until arrD[dog + 1]) {
            if (arrD[pos] == -1){continue}
            val linkPoint = arrD[pos].div(10) * 3 + arrD[pos].mod(10)
            minus[2] = linkPoint
            if (board[linkPoint] != 1 && board[linkPoint] != 2 && minus[0] != minus[2]) { //省略狗子和兔子
                board[linkPoint] = 1
                board[dog] = 0
                val score = minimax(Int.MAX_VALUE, Int.MIN_VALUE, isRabbit = true, board = board, depth = 1)
                board[linkPoint] = 0 //還原
                board[dog] = 1
                if (score >= bestScore) {
                    fromDog = dog
                    bestScore = score
                    bestMove = linkPoint // 取最優
                }
            }
        }
    }
    return arrayListOf(bestMove,fromDog)

}
var minus = arrayListOf(14,14,14)
val colors = arrayListOf(0,11,0,18,13,18,13,11,13,18,13,18,0,11)
fun minimax(alph: Int ,beta: Int ,board: ArrayList<Int>, isRabbit: Boolean, depth: Int = 5): Int {
    if(depth == 0){return 0}
    val rabbit = board.indexOf(2)
    //先找出最先跟最後的狗子
    val dogs = arrayListOf<Int>()
    for ((i, item) in board.withIndex()){
        if (item == 1){
            dogs.add(i)
        }
    }
    var sum = 0
    sum += colors[rabbit]
    for (dog in dogs){
        sum += colors[dog]
    }
    var c = 0
    if (rabbit.div(3) < dogs[0].div(3)){c++}
    if (rabbit.div(3) < dogs[1].div(3)){c++}
    if (rabbit.div(3) < dogs[2].div(3)){c++}
    if (c >= 2 || board[1] == 2 || board[7] == 2) {
        return -10 + rabbit.div(3)  - c
    } else {
        if ((board[9] == 1 && board[10] == 1 && board[11] == 1) ||
            (board[3] == 1 && board[7] == 1 && board[9] == 1 && rabbit == 6) ||
            (board[5] == 1 && board[7] == 1 && board[11] == 1 && rabbit == 8) ||
            (board[5]==1 && board[7]==1 && board[9]==1)||
            (board[3]==1 && board[7]==1 && board[11]==1)||
            (dogs.maxOrNull()!!.div(3) - dogs.minOrNull()!!.div(3)) < 2
              ) { return 10 + if(sum == 60){15}else{0} + if(board[7]==1){5}else{0}
        } else {
            if (isRabbit) { //兔子
                var a = alph
                for (pos in arrR[rabbit] until arrR[rabbit + 1]) {
                    if (arrR[pos] == -1){continue}
                    val linkPoint = arrR[pos].div(10) * 3 + arrR[pos].mod(10)
                    if (board[linkPoint] != 1) { //省略狗子
                        board[linkPoint] = 2
                        board[rabbit] = 0
                        a = min(a, minimax(a, beta, board, false, depth - 1))
                        board[linkPoint] = 0 //還原
                        board[rabbit] = 2
                        if(a >= beta){ break }
                    }
                }
                return a
            } else { //狗子
                var b = beta
                for (dog in dogs) { // 每隻狗子
                    for (pos in arrD[dog] until arrD[dog + 1]) {
                        if (arrD[pos] == -1){continue}
                        val linkPoint = arrD[pos].div(10) * 3 + arrD[pos].mod(10)
                        if (board[linkPoint] != 1 && board[linkPoint] != 2) { //省略狗子跟兔子
                            board[linkPoint] = 1
                            board[dog] = 0
                            b = max(b, minimax(alph, b, board, true, depth - 1))
                            board[linkPoint] = 0 //還原
                            board[dog] = 1
                            if(alph >= b){ break }
                        }
                    }
                }
                return b
            }
        }
    }
}

@Composable
fun Computer(navController: NavController) {
    isStart.value = false
    Box {
        Box(modifier = Modifier
            .padding(top = 70.dp, start = LocalConfiguration.current.screenWidthDp.dp - 32.dp)
            .size(22.dp)
        ){
            Image(painterResource(id = R.drawable.outline_power_settings_new_black_48),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        exitAPP()
                    })
        }
        Box(modifier = Modifier
            .padding(top = 70.dp, start = 10.dp)
            .size(22.dp)
        ){
            Image(painterResource(id = R.drawable.outline_undo_black_48),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        navController.popBackStack()
                    })
        }
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "選擇角色",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp / 4)
            )
            ElevatedCard(modifier = Modifier
                .padding(top = LocalConfiguration.current.screenHeightDp.dp / 8)
                .clickable { navController.navigate("PlayDog") }) {
                Row(modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)){
                    Image(painterResource(id = R.drawable.dog),null,modifier = Modifier
                        .padding(start=20.dp).size(50.dp))
                Text(text = "獵犬",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, end = 25.dp)
                )}
            }
            ElevatedCard(modifier = Modifier
                .padding(top = LocalConfiguration.current.screenHeightDp.dp / 8)
                .clickable { navController.navigate("PlayRabbit") }) {
                Row(modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)){
                    Image(painterResource(id = R.drawable.rabbit), null, modifier = Modifier
                        .padding(start=20.dp).size(50.dp))
                    Text(text = "兔子",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 20.dp, top = 10.dp, end = 25.dp)
                    )
                }
            }

        }
    }
}