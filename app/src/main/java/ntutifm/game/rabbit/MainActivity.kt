package ntutifm.game.rabbit

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
@Preview
@Composable
fun Preview1() {
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background) {
        Greeting()
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
        val dog2 = remember { mutableStateOf(3) }
        val dog3 = remember { mutableStateOf(5) }
        val rabbit = remember { mutableStateOf(41) }
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
        val dogs = mutableListOf(
            1,
            3,
            5)
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
        val arr = arrayListOf(15,
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
            -1)
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
            if(current.value == dog3.value){
                pics[dog3.value].value = R.drawable.dogselect
            }
        }

        fun indexOf(array:MutableList<Int>,element:Int):Int{
            array.indexOf(element)
            return -1
        }
        fun movement(picNum: Int){
            if (current.value != picNum && pics[picNum].value == R.drawable.selected) {
                //if(current.value in dogs){
                //    Log.d("mm","${dogs.indexOf(current.value)}")
                //}
                //dogs[dogs.indexOf(current.value)]= picNum
                dog2.value = picNum
                pics[current.value].value = R.drawable.dot
                (arr[current.value]until arr[current.value+1]).forEach {
                    if (pics[arr[it].div(10)*3 + arr[it].mod(10)].value == R.drawable.selected) {
                        pics[arr[it].div(10)*3 + arr[it].mod(10)].value = R.drawable.dot //此處要查圖
                    }
                }
                current.value = 50
            } else {
                if (pics[picNum].value == R.drawable.dog) {
                    current.value = picNum
                    (arr[picNum]until arr[picNum+1]).forEach {
                        if (arr[it].div(10)*3 + arr[it].mod(10) != dog1.value && arr[it].div(10)*3 + arr[it].mod(10) != dog2.value && arr[it].div(10)*3 + arr[it].mod(10) != dog3.value && arr[it] != rabbit.value) {
                            pics[arr[it].div(10)*3 + arr[it].mod(10)].value = R.drawable.selected //此處要查圖
                        }
                    }
                } else {
                    if (pics[picNum].value == R.drawable.dogselect) {
                        current.value = 50
                        (arr[picNum]until arr[picNum+1]).forEach {
                            if (pics[arr[it].div(10)*3 + arr[it].mod(10)].value == R.drawable.selected) {
                                pics[arr[it].div(10)*3 + arr[it].mod(10)].value = R.drawable.dot //此處要查圖
                            }
                        }
                    }
                }
            }
        }
        //1
        Box(modifier = Modifier
            .padding(top = 599.dp, start = 160.dp)
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
        Box(modifier = Modifier
            .padding(top = 480.dp, start = 271.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic10.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(3)
                    })
        }
        //4
        Box(modifier = Modifier
            .padding(top = 480.dp, start = 160.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic11.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(4)
                    })

        }
        //5
        Box(modifier = Modifier
            .padding(top = 480.dp, start = 44.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic12.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(5)
                    })
        }

        //6
        Box(modifier = Modifier
            .padding(top = 361.dp, start = 271.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic20.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(6)
                    })
        }
        //7
        Box(modifier = Modifier
            .padding(top = 361.dp, start = 161.dp)
            .size(75.dp)) {
            Image(painterResource(id = pic21.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(7)
                    })
        }
        //8
        Box(modifier = Modifier
            .padding(top = 361.dp, start = 44.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic22.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(8)
                    })
        }
        //9
        Box(modifier = Modifier
            .padding(top = 242.dp, start = 271.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic30.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(9)
                    })
        }
        //10
        Box(modifier = Modifier
            .padding(top = 242.dp, start = 161.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic31.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(10)
                    })
        }
        //11
        Box(modifier = Modifier
            .padding(top = 242.dp, start = 43.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic32.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(11)
                    })
        }
        //13
        Box(modifier = Modifier
            .padding(top = 120.dp, start = 161.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic41.value),
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        movement(13)
                    })
        }
    }
}
