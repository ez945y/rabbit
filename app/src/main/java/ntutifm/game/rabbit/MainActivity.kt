package ntutifm.game.rabbit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ntutifm.game.rabbit.ui.theme.RabbitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RabbitTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {

        Image(
            painterResource(id = R.drawable.background), null,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            alignment = Alignment.Center,
        )
        val dog1 = remember { mutableStateOf(1) }
        val dog2 = remember { mutableStateOf(20) }
        val dog3 = remember { mutableStateOf(22) }
        val pic1 = remember { mutableStateOf(R.drawable.dog) }
        val pic3 = remember { mutableStateOf(R.drawable.dog) }
        val pic4 = remember { mutableStateOf(R.drawable.dot) }
        val pic5 = remember { mutableStateOf(R.drawable.dog) }
        val pic6 = remember { mutableStateOf(R.drawable.dot) }
        val pic7 = remember { mutableStateOf(R.drawable.dot) }
        val pic8 = remember { mutableStateOf(R.drawable.dot) }
        val pic9 = remember { mutableStateOf(R.drawable.dot) }
        val pic10 = remember { mutableStateOf(R.drawable.dot) }
        val pic11 = remember { mutableStateOf(R.drawable.dot) }
        val pic13 = remember { mutableStateOf(R.drawable.rabbit) }
        var temp = remember { mutableStateOf(pic1) }
        var lis = mutableListOf(pic1, pic4, pic6, pic7)
        //1
        Box(modifier = Modifier
            .padding(top = 599.dp, start = 160.dp)
            .size(80.dp)
        )
        {
            Image(painterResource(id = pic1.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        if (temp != pic1 && pic1.value == R.drawable.selected) {
                            pic1.value = R.drawable.dog
                            temp.value.value = R.drawable.dot
                            lis.forEach {
                                if (it.value == R.drawable.selected) {
                                    it.value = R.drawable.dot
                                }
                            }
                        } else {
                            lis = mutableListOf(pic3, pic4, pic5)
                            if (pic1.value == R.drawable.dog) {
                                pic1.value = R.drawable.dogselect
                                lis.forEach {
                                    if (it.value != R.drawable.dog && it.value != R.drawable.rabbit) {
                                        it.value = R.drawable.selected //此處要查圖
                                    }
                                }
                                temp.value = pic1
                            } else {
                                if (pic1.value == R.drawable.dogselect) {
                                    pic1.value = R.drawable.dog
                                    lis.forEach {
                                        if (it.value == R.drawable.selected) {
                                            it.value = R.drawable.dot //此處要查圖
                                        }
                                    }
                                }
                            }
                        }
                    })
        }
        //3
        Box(modifier = Modifier
            .padding(top = 480.dp, start = 271.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic3.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        if (temp != pic3 && pic3.value == R.drawable.selected) {
                            pic3.value = R.drawable.dog
                            temp.value.value = R.drawable.dot
                            lis.forEach {
                                if (it.value == R.drawable.selected) {
                                    it.value = R.drawable.dot //此處要查圖
                                }
                            }
                        } else {
                            lis = mutableListOf(pic1, pic4, pic6, pic7)
                            if (pic3.value == R.drawable.dog) {
                                pic3.value = R.drawable.dogselect
                                lis.forEach {
                                    if (it.value != R.drawable.dog && it.value != R.drawable.rabbit) {
                                        it.value = R.drawable.selected //此處要查圖
                                    }
                                }
                                temp.value = pic3
                            } else {
                                if (pic3.value == R.drawable.dogselect) {
                                    pic3.value = R.drawable.dog
                                    lis.forEach {
                                        if (it.value == R.drawable.selected) {
                                            it.value = R.drawable.dot //此處要查圖
                                        }
                                    }
                                }
                            }
                        }
                    })
        }
        //4
        Box(modifier = Modifier
            .padding(top = 480.dp, start = 160.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic4.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        if (temp != pic4 && pic4.value == R.drawable.selected) {
                            pic4.value = R.drawable.dog
                            temp.value.value = R.drawable.dot
                            lis.forEach {
                                if (it.value == R.drawable.selected) {
                                    it.value = R.drawable.dot
                                }
                            }
                        } else {
                            lis = mutableListOf(pic1, pic3, pic5, pic7)
                            if (pic4.value == R.drawable.dog) {
                                pic4.value = R.drawable.dogselect
                                lis.forEach {
                                    if (it.value != R.drawable.dog && it.value != R.drawable.rabbit) {
                                        it.value = R.drawable.selected //此處要查圖
                                    }
                                }
                                temp.value = pic4
                            } else {
                                if (pic4.value == R.drawable.dogselect) {
                                    pic4.value = R.drawable.dog
                                    lis.forEach {
                                        if (it.value == R.drawable.selected) {
                                            it.value = R.drawable.dot //此處要查圖
                                        }
                                    }
                                }
                            }
                        }
                    })

        }
        //5
        Box(modifier = Modifier
            .padding(top = 480.dp, start = 44.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic5.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        if (temp != pic5 && pic5.value == R.drawable.selected) {
                            pic5.value = R.drawable.dog
                            temp.value.value = R.drawable.dot
                            lis.forEach {
                                if (it.value == R.drawable.selected) {
                                    it.value = R.drawable.dot
                                }
                            }
                        } else {
                            lis = mutableListOf(pic1, pic4, pic7, pic8)
                            if (pic5.value == R.drawable.dog) {
                                pic5.value = R.drawable.dogselect
                                lis.forEach {
                                    if (it.value != R.drawable.dog && it.value != R.drawable.rabbit) {
                                        it.value = R.drawable.selected //此處要查圖
                                    }
                                }
                                temp.value = pic5
                            } else {
                                if (pic5.value == R.drawable.dogselect) {
                                    pic5.value = R.drawable.dog
                                    lis.forEach {
                                        if (it.value == R.drawable.selected) {
                                            it.value = R.drawable.dot //此處要查圖
                                        }
                                    }
                                }
                            }
                        }
                    })
        }
        fun movement(pic: MutableState<Int>): Unit {
            if (temp != pic && pic.value == R.drawable.selected) {
                pic.value = R.drawable.dog
                temp.value.value = R.drawable.dot
                lis.forEach {
                    if (it.value == R.drawable.selected) {
                        it.value = R.drawable.dot
                    }
                }

            } else {
                lis = mutableListOf(pic1, pic4, pic7, pic8)
                if (pic.value == R.drawable.dog) {
                    pic.value = R.drawable.dogselect
                    lis.forEach {
                        if (it.value != R.drawable.dog && it.value != R.drawable.rabbit) {
                            it.value = R.drawable.selected //此處要查圖
                        }
                    }
                    temp.value = pic
                } else {
                    if (pic.value == R.drawable.dogselect) {
                        pic.value = R.drawable.dog
                        lis.forEach {
                            if (it.value == R.drawable.selected) {
                                it.value = R.drawable.dot //此處要查圖
                            }
                        }
                    }
                }
            }
        }
        //6
        Box(modifier = Modifier
            .padding(top = 361.dp, start = 271.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic6.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(pic6)
                    })
        }
        //7
        Box(modifier = Modifier
            .padding(top = 361.dp, start = 161.dp)
            .size(75.dp)) {
            Image(painterResource(id = pic7.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(pic7)
                    })
        }
        //8
        Box(modifier = Modifier
            .padding(top = 361.dp, start = 44.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic8.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(pic8)
                    })
        }
        //9
        Box(modifier = Modifier
            .padding(top = 242.dp, start = 271.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic9.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(pic9)
                    })
        }
        //10
        Box(modifier = Modifier
            .padding(top = 242.dp, start = 161.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic10.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(pic10)
                    })
        }
        //11
        Box(modifier = Modifier
            .padding(top = 242.dp, start = 43.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic11.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(pic11)
                    })
        }
        //13
        Box(modifier = Modifier
            .padding(top = 120.dp, start = 161.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic13.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(pic13)
                    })
        }
    }
}
