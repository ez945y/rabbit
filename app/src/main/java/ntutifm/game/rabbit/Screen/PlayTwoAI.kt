package ntutifm.game.rabbit.Screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ntutifm.game.rabbit.R
import java.util.*


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PlayTwoAI(navController: NavController) {
    val height = remember { mutableStateOf(790.dp) }
    val weight = remember { mutableStateOf(395.dp) }
    val flag = remember { mutableStateOf(true) }
    val showAlertDialog = remember { mutableStateOf(false) }
    val stack = remember { ArrayDeque<Int>() }
    val stackD = remember { ArrayDeque<Int>() }
    val dog1 = remember { mutableStateOf(1) }
    val dog2 = remember { mutableStateOf(3) }
    val dog3 = remember { mutableStateOf(5) }
    val rabbit = remember { mutableStateOf(13) }
    val current = remember { mutableStateOf(50) }
    val pic01 = remember { mutableStateOf(R.drawable.dog) }
    val pic10 = remember { mutableStateOf(R.drawable.dog) }
    val pic11 = remember { mutableStateOf(R.drawable.dot) }
    val pic12 = remember { mutableStateOf(R.drawable.dog) }
    val pic20 = remember { mutableStateOf(R.drawable.dot) }
    val pic21 = remember { mutableStateOf(R.drawable.dot) }
    val pic22 = remember { mutableStateOf(R.drawable.dot) }
    val pic30 = remember { mutableStateOf(R.drawable.dot) }
    val pic31 = remember { mutableStateOf(R.drawable.dot) }
    val pic32 = remember { mutableStateOf(R.drawable.dot) }
    val pic41 = remember { mutableStateOf(R.drawable.rabbit) }
    val winP = remember { mutableStateOf("") }
    val dogs = arrayListOf(
        dog1.value,
        dog2.value,
        dog3.value
    )

    val pics = mutableListOf(
        pic01,
        pic01,
        pic01,
        pic10,
        pic11,
        pic12,
        pic20,
        pic21,
        pic22,
        pic30,
        pic31,
        pic32,
        pic41,
        pic41
    )
    fun re() {
        for (item in pics) {
            when(item.value){
                R.drawable.selected -> item.value = R.drawable.dot
                R.drawable.dogselect -> item.value = R.drawable.dog
                R.drawable.rabbitselect -> item.value = R.drawable.rabbit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .size(weight.value, height.value)
                .padding(top = 50.dp)
        ) {
            Image(
                painterResource(id = R.drawable.background), null,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .size(weight.value, height.value)
                    .padding(top = 50.dp),
                alignment = Alignment.Center,
            )

            if (flag.value && !showAlertDialog.value) {
                Thread.sleep(500)
                val board = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                board[dog1.value] = 1
                board[dog2.value] = 1
                board[dog3.value] = 1
                board[rabbit.value] = 2
                Log.e("mm","D")
                Log.e("mm","$board")
                stack.push(rabbit.value)
                val bestMove = findMoveD(board) //改二
                minus[0] = minus[1]
                minus[1] = bestMove[0]
                current.value = bestMove[1]
                dogs[0] = dog1.value
                dogs[1] = dog2.value
                dogs[2] = dog3.value
                stack.push(dogs[dogs.indexOf(current.value)])
                when (dogs.indexOf(current.value)) {
                    0 -> {pics[bestMove[0]].value = R.drawable.dog
                        dog1.value = bestMove[0]}
                    1 -> {pics[bestMove[0]].value = R.drawable.dog
                        dog2.value = bestMove[0]}
                    2 -> {pics[bestMove[0]].value = R.drawable.dog
                        dog3.value = bestMove[0]}
                }
                dogs[dogs.indexOf(current.value)] = bestMove[0]
                stackD.push(dogs[dogs.indexOf(bestMove[0])])
                Log.d("DD",dogs.toString())
                pics[bestMove[1]].value = R.drawable.dot
                if ((dogs.indexOf(9) > -1 && dogs.indexOf(10) > -1 && dogs.indexOf(11) > -1 ) ||
                    (dogs.indexOf(3) > -1 && dogs.indexOf(7) > -1 && dogs.indexOf(9) > -1 && rabbit.value == 6) ||
                    (dogs.indexOf(5) > -1 && dogs.indexOf(7) > -1 && dogs.indexOf(11) > -1 && rabbit.value == 8) ) {
                    Log.e("mm", "DogWIN")
                    winP.value = "Dog Win"
                    showAlertDialog.value = true
                }
                re()
                current.value = 50
                flag.value = false
            }else{
                if (!flag.value && !showAlertDialog.value) {
                    Thread.sleep(500)
                    val board = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                    board[dog1.value] = 1
                    board[dog2.value] = 1
                    board[dog3.value] = 1
                    board[rabbit.value] = 2
                    Log.e("mm","R")
                    Log.e("mm","$board")
                    stack.push(rabbit.value)
                    val pre = board.indexOf(2)
                    rabbit.value = findMoveR(board)
                    pics[rabbit.value].value = R.drawable.rabbit
                    pics[pre].value = R.drawable.dot
                    var c = 0
                    if (rabbit.value.div(3) < dog1.value.div(3)){c++}
                    if (rabbit.value.div(3) < dog2.value.div(3)){c++}
                    if (rabbit.value.div(3) < dog3.value.div(3)){c++}
                    if (c >= 2) {
                        Log.e("mm", "RabbitWIN")
                        winP.value = "Rabbit Win"
                        showAlertDialog.value = true
                    }
                    flag.value = true
                }
            }

            //1
            Box(
                modifier = Modifier
                    .padding(top = 599.dp, start = 159.dp)
                    .size(80.dp)
            )
            {
                Image(painterResource(id = pic01.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })
            }
            //3
            Box(
                modifier = Modifier
                    .padding(top = 480.dp, start = 272.dp)
                    .size(80.dp)
            ) {
                Image(painterResource(id = pic10.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })
            }
            //4
            Box(
                modifier = Modifier
                    .padding(top = 480.dp, start = 158.dp)
                    .size(80.dp)
            ) {
                Image(painterResource(id = pic11.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })

            }
            //5
            Box(
                modifier = Modifier
                    .padding(top = 480.dp, start = 47.dp)
                    .size(80.dp)
            ) {
                Image(painterResource(id = pic12.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })
            }

            //6
            Box(
                modifier = Modifier
                    .padding(top = 360.dp, start = 272.dp)
                    .size(80.dp)
            ) {
                Image(painterResource(id = pic20.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })
            }
            //7
            Box(
                modifier = Modifier
                    .padding(top = 361.dp, start = 160.dp)
                    .size(75.dp)
            ) {
                Image(painterResource(id = pic21.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })
            }
            //8
            Box(
                modifier = Modifier
                    .padding(top = 360.dp, start = 46.dp)
                    .size(80.dp)
            ) {
                Image(painterResource(id = pic22.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })
            }
            //9
            Box(
                modifier = Modifier
                    .padding(top = 244.dp, start = 271.dp)
                    .size(80.dp)
            ) {
                Image(painterResource(id = pic30.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })
            }
            //10
            Box(
                modifier = Modifier
                    .padding(top = 244.dp, start = 158.dp)
                    .size(80.dp)
            ) {
                Image(painterResource(id = pic31.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })
            }
            //11
            Box(
                modifier = Modifier
                    .padding(top = 244.dp, start = 47.dp)
                    .size(80.dp)
            ) {
                Image(painterResource(id = pic32.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })
            }
            //13
            Box(
                modifier = Modifier
                    .padding(top = 120.dp, start = 159.dp)
                    .size(80.dp)
            ) {
                Image(painterResource(id = pic41.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                        })
            }
            if (showAlertDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        showAlertDialog.value = false
                    },
                    title = {
                        Text("結束", Modifier.padding(start = 80.dp))
                    },
                    text = {
                        Text(winP.value, Modifier.padding(start = 70.dp))
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showAlertDialog.value = false
                                navController.popBackStack()
                            },
                            modifier = Modifier.padding(end = 80.dp)
                        )
                        {
                            Text(text = "確認")
                        }
                    },
                )
            }
        }
        Row(Modifier.padding(start = 140.dp, top = 70.dp)) {
            Text(
                text = if (flag.value) {
                    "回合:DOG"
                } else {
                    "回合:RABBIT"
                }
            )
        }
    }
}
