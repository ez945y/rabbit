package ntutifm.game.rabbit.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ntutifm.game.rabbit.R
import ntutifm.game.rabbit.isStart
import ntutifm.game.rabbit.serviceStatus
import java.util.*

@Composable
fun PlayRabbit(navController: NavController) {
    val flag = remember { mutableStateOf(true) }
    val showAlertDialog = remember { mutableStateOf(false) }
    val dog1 = remember { mutableStateOf(1) }
    val dog2 = remember { mutableStateOf(3) }
    val dog3 = remember { mutableStateOf(5) }
    val rabbit = remember { mutableStateOf(13) }
    val stack = remember { ArrayDeque<Int>() }
    val stackD = remember { ArrayDeque<Int>() }
    val current = remember { mutableStateOf(50) }
    val current2 = remember { mutableStateOf(50) }
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
        dog3.value)
    isStart.value = false
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
        pic41)

    fun re() {
        for (item in pics) {
            if (item.value == R.drawable.selected) {
                item.value = R.drawable.dot
            }
            if (item.value == R.drawable.dogselect) {
                item.value = R.drawable.dog
            }
            if (item.value == R.drawable.rabbitselect) {
                item.value = R.drawable.rabbit
            }
        }
    }
    if (current.value != dog1.value && current.value != dog2.value && current.value != dog3.value) {
        pics[dog1.value].value = R.drawable.dog
        pics[dog2.value].value = R.drawable.dog
        pics[dog3.value].value = R.drawable.dog
    }
    if (current2.value != rabbit.value) {
        pics[rabbit.value].value = R.drawable.rabbit
    } else {
        if (current2.value == rabbit.value) {
            pics[rabbit.value].value = R.drawable.rabbitselect
        }
    }


    if (flag.value && !showAlertDialog.value) {
        val board = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        board[dogs[0]] = 1
        board[dogs[1]] = 1
        board[dogs[2]] = 1
        board[rabbit.value] = 2
        val bestMove = findMoveD(board) //改二
        minus[0] = minus[1]
        minus[1] = bestMove[0]
        current.value = bestMove[1]
        stack.push(dogs[dogs.indexOf(current.value)])
        when (dogs.indexOf(current.value)) {
            0 -> dog1.value = bestMove[0]
            1 -> dog2.value = bestMove[0]
            2 -> dog3.value = bestMove[0]
        }
        dogs[dogs.indexOf(current.value)] = bestMove[0]
        stackD.push(dogs[dogs.indexOf(bestMove[0])])
        pics[bestMove[1]].value = R.drawable.dot
        if ((dogs.indexOf(9) > -1 && dogs.indexOf(10) > -1 && dogs.indexOf(11) > -1) ||
            (dogs.indexOf(3) > -1 && dogs.indexOf(7) > -1 && dogs.indexOf(9) > -1 && rabbit.value == 6) ||
            (dogs.indexOf(5) > -1 && dogs.indexOf(7) > -1 && dogs.indexOf(11) > -1 && rabbit.value == 8)
        ) {
            winP.value = "Dog Win"
            showAlertDialog.value = true
        }
        re()
        current.value = 50
        flag.value = false
    }

    fun movement(picNum: Int) {

        if (!flag.value) { //兔子
            if (current2.value != picNum && pics[picNum].value == R.drawable.selected) {
                stack.push(rabbit.value)
                rabbit.value = picNum
                var c = 0
                if (rabbit.value.div(3) < dog1.value.div(3)) {
                    c++
                }
                if (rabbit.value.div(3) < dog2.value.div(3)) {
                    c++
                }
                if (rabbit.value.div(3) < dog3.value.div(3)) {
                    c++
                }
                if (c >= 2) {
                    winP.value = "Rabbit Win"
                    showAlertDialog.value = true
                }
                pics[current2.value].value = R.drawable.dot
                re()
                current2.value = 50
                flag.value = true
            } else {
                if (pics[picNum].value == R.drawable.rabbit) {
                    current2.value = picNum
                    re()
                    (arrR[picNum] until arrR[picNum + 1]).forEach {
                        val linkPoint = arrR[it].div(10) * 3 + arrR[it].mod(10)
                        if (dogs.indexOf(linkPoint) < 0 && linkPoint != rabbit.value &&
                            arrR[it] >= 0
                        ) {
                            pics[linkPoint].value = R.drawable.selected //此處要查圖
                        }
                    }

                } else {
                    if (pics[picNum].value == R.drawable.rabbitselect) {
                        current2.value = 50
                        re()
                    }
                }
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
            .fillMaxSize()
        ) {
            //line/
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 351,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 11)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {Image(painterResource(R.drawable.line1),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line\
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 351,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 5)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {Image(painterResource(R.drawable.line2),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line/
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 651,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 5)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {Image(painterResource(R.drawable.line1),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line\
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 651,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 11)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {Image(painterResource(R.drawable.line2),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line/
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 951,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 11)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {Image(painterResource(R.drawable.line1),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line\
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 951,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 5)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {Image(painterResource(R.drawable.line2),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line/
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 1251,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 5)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {Image(painterResource(R.drawable.line1),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line\
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 1251,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 11)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {Image(painterResource(R.drawable.line2),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }


            //1
            Box(
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 5,
                        start = LocalConfiguration.current.screenWidthDp.dp / 5 * 2)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            )
            {
                Image(painterResource(id = pic01.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(1)
                        })
            }
            //3
            Box(
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 2,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10 * 7)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {
                Image(painterResource(id = pic10.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(3)
                        })
            }
            //4
            Box(
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 2,
                        start = LocalConfiguration.current.screenWidthDp.dp / 5 * 2)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {
                Image(painterResource(id = pic11.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(4)
                        })

            }
            //5
            Box(
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 2,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {
                Image(painterResource(id = pic12.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(5)
                        })
            }

            //6
            Box(
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 10 * 8,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10 * 7)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {
                Image(painterResource(id = pic20.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(6)
                        })
            }
            //7
            Box(
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 10 * 8,
                        start = LocalConfiguration.current.screenWidthDp.dp / 5 * 2)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {
                Image(painterResource(id = pic21.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(7)
                        })
            }
            //8
            Box(
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 10 * 8,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {
                Image(painterResource(id = pic22.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(8)
                        })
            }
            //9
            Box(
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 10 * 11,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10 * 7)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {
                Image(painterResource(id = pic30.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(9)
                        })
            }
            //10
            Box(
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 10 * 11,
                        start = LocalConfiguration.current.screenWidthDp.dp / 5 * 2)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {
                Image(painterResource(id = pic31.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(10)
                        })
            }
            //11
            Box(
                modifier = Modifier
                    .padding(top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 10 * 11,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {
                Image(painterResource(id = pic32.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(11)
                        })
            }
            //13
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 10 * 14,
                        start = LocalConfiguration.current.screenWidthDp.dp / 5 * 2)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 5)
            ) {
                Image(painterResource(id = pic41.value),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            movement(13)
                        })
            }
            //line-
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 100 * 105,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10 * 3)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line3),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line-
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 100 * 105,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10 * 6)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line3),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line-
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 100 * 75,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10 * 3)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line3),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line-
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 100 * 75,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10 * 6)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line3),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line-
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 100 * 45,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10 * 3)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line3),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line-
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 100 * 45,
                        start = LocalConfiguration.current.screenWidthDp.dp / 10 * 6)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line3),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line|
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 901,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 3)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line4),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line|
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 901,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 9)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line4),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line|
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 901,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 15)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line4),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line|
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 601,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 3)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line4),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line|
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 601,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 9)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line4),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line|
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 601,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 15)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line4),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line|
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 1201,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 9)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line4),
                null,
                modifier = Modifier
                    .fillMaxSize())
            }
            //line|
            Box(
                modifier = Modifier
                    .padding( top = LocalConfiguration.current.screenHeightDp.dp - LocalConfiguration.current.screenWidthDp.dp / 1000 * 301,
                        start = LocalConfiguration.current.screenWidthDp.dp / 20 * 9)
                    .size(LocalConfiguration.current.screenWidthDp.dp / 10)
            ) {Image(painterResource(R.drawable.line4),
                null,
                modifier = Modifier
                    .fillMaxSize())
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
        Row(Modifier.padding(start = 100.dp, top = 70.dp)) {
            Text(text = if (flag.value) {
                "回合:DOG"
            } else {
                "回合:RABBIT"
            })
            Row(modifier = Modifier.clickable {
                if (!stack.isEmpty()) {
                    var before = stack.pop() //狗
                    val after = stackD.pop() //狗

                    pics[dogs[dogs.indexOf(after)]].value = R.drawable.dot
                    when (dogs.indexOf(after)) {
                        0 -> dog1.value = before
                        1 -> dog2.value = before
                        2 -> dog3.value = before
                    }
                    dogs[dogs.indexOf(after)] = before


                    before = stack.pop()
                    pics[rabbit.value].value = R.drawable.dot
                    rabbit.value = before
                    current.value = 50
                    current2.value = 50

                    re()
                }
            }) {
                Image(painterResource(id = R.drawable.backround), null, modifier = Modifier
                    .padding(top = 4.dp, start = 20.dp, end = 5.dp)
                    .size(22.dp))
                Text("悔棋")
            }

            if (serviceStatus.value) {
                Icon(painterResource(id = R.drawable.ic_baseline_volume_up_24),
                    null,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 15.dp)
                        .clickable { serviceStatus.value = !serviceStatus.value })
            } else {
                Icon(painterResource(id = R.drawable.ic_baseline_volume_off_24),
                    null,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 15.dp)
                        .clickable { serviceStatus.value = !serviceStatus.value })
            }
        }
    }
}