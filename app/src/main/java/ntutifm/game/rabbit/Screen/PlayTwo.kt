package ntutifm.game.rabbit.Screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ntutifm.game.rabbit.R
import ntutifm.game.rabbit.serviceStatus
import java.util.*


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PlayTwo(navController: NavController) {
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
    val current2 = remember { mutableStateOf(50) }
    val sameMove = remember { mutableStateOf(0) }
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
            when (item.value) {
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

            if (current.value != dog1.value && current.value != dog2.value && current.value != dog3.value) {
                pics[dog1.value].value = R.drawable.dog
                pics[dog2.value].value = R.drawable.dog
                pics[dog3.value].value = R.drawable.dog
            } else {
                if (current.value == dog1.value) {
                    pics[dog1.value].value = R.drawable.dogselect
                }
                if (current.value == dog2.value) {
                    pics[dog2.value].value = R.drawable.dogselect
                }
                if (current.value == dog3.value) {
                    pics[dog3.value].value = R.drawable.dogselect
                }
            }
            if (current2.value != rabbit.value) {
                pics[rabbit.value].value = R.drawable.rabbit
            } else {
                if (current2.value == rabbit.value) {
                    pics[rabbit.value].value = R.drawable.rabbitselect
                }
            }

            fun movement(picNum: Int) {

                if (flag.value) { //狗子
                    if (current.value != picNum && pics[picNum].value == R.drawable.selected) {
                        if (stack.indexOf(picNum) == 1) {
                            sameMove.value++
                            if (sameMove.value == 9) {
                                winP.value = "Rabbit Win"
                                showAlertDialog.value = true
                            }
                        } else {
                            sameMove.value = 0
                        }
                        stack.push(dogs[dogs.indexOf(current.value)])
                        when (dogs.indexOf(current.value)) {
                            0 -> dog1.value = picNum
                            1 -> dog2.value = picNum
                            2 -> dog3.value = picNum
                        }
                        dogs[dogs.indexOf(current.value)] = picNum
                        stackD.push(dogs[dogs.indexOf(picNum)])
                        Log.e("mm", dogs.toString())
                        if ((dogs.indexOf(9) > -1 && dogs.indexOf(10) > -1 && dogs.indexOf(11) > -1) ||
                            (dogs.indexOf(3) > -1 && dogs.indexOf(7) > -1 && dogs.indexOf(9) > -1) ||
                            (dogs.indexOf(5) > -1 && dogs.indexOf(7) > -1 && dogs.indexOf(11) > -1)
                        ) {
                            Log.e("mm", "DogWIN")
                            winP.value = "Dog Win"
                            showAlertDialog.value = true
                        }
                        pics[current.value].value = R.drawable.dot
                        re()
                        current.value = 50
                        flag.value = false
                    } else {
                        if (pics[picNum].value == R.drawable.dog) {
                            current.value = picNum
                            re()
                            (arrD[picNum] until arrD[picNum + 1]).forEach {
                                val linkPoint = arrD[it].div(10) * 3 + arrD[it].mod(10)
                                if (dogs.indexOf(linkPoint) < 0 && linkPoint != rabbit.value &&
                                    arrD[it] >= 0
                                ) {
                                    pics[linkPoint].value = R.drawable.selected //此處要查圖
                                }
                            }
                        } else {
                            if (pics[picNum].value == R.drawable.dogselect) {
                                current.value = 50
                                re()
                            }
                        }
                    }
                } else { //兔子
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
                            Log.e("mm", "RabbitWIN")
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
                                //                        stack.push(linkPoint)
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
                            movement(1)
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
                            movement(3)
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
                            movement(4)
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
                            movement(5)
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
                            movement(6)
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
                            movement(7)
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
                            movement(8)
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
                            movement(9)
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
                            movement(10)
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
                            movement(11)
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
                            movement(13)
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
        Row(Modifier.padding(start = 100.dp, top = 70.dp)) {
            Text(
                text = if (flag.value) {
                    "回合:DOG"
                } else {
                    "回合:RABBIT"
                }
            )
            Image(
                painterResource(id = R.drawable.backround), null, modifier = Modifier
                    .padding(top = 4.dp, start = 20.dp, end = 5.dp)
                    .size(22.dp)
            )
            Text("悔棋", modifier = Modifier.clickable {
                if (!stack.isEmpty()) {
                    val before = stack.pop()
                    if (flag.value) {
                        pics[rabbit.value].value = R.drawable.dot
                        rabbit.value = before
                        flag.value = false
                    } else {
                        val after = stackD.pop()
                        pics[dogs[dogs.indexOf(after)]].value = R.drawable.dot
                        when (dogs.indexOf(after)) {
                            0 -> dog1.value = before
                            1 -> dog2.value = before
                            2 -> dog3.value = before
                        }
                        dogs[dogs.indexOf(after)] = before
                        flag.value = true
                    }
                    current.value = 50
                    current2.value = 50
                    re()
                }
            })
            if (serviceStatus.value) {
                Icon(painterResource(id = R.drawable.ic_baseline_volume_up_24),
                    null,
                    modifier = Modifier.padding(top = 4.dp, start = 15.dp).clickable {serviceStatus.value = !serviceStatus.value })
            }else{
                Icon(painterResource(id = R.drawable.ic_baseline_volume_off_24),
                    null,
                    modifier = Modifier.padding(top = 4.dp, start = 15.dp).clickable {serviceStatus.value = !serviceStatus.value })
            }
        }
    }
}
