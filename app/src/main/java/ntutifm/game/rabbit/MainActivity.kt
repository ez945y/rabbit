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
import java.util.ArrayDeque



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
        val rabbit = remember { mutableStateOf(13) }
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
        val dogs = arrayListOf(
            dog1.value,
            dog2.value,
            dog3.value)
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
        val arrD = arrayListOf(15,16,19,20,23,26,29,31,36,38,40,43,45,46,47,-1,10,11,12,
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
        val arrR = arrayListOf(15,16,17,18,22,26,30,33,41,44,48,52,56,57,60,-1,-1,-1,20,21,11,1,21,10,12,1,21,22,11,1,30,21,10,30,31,32,20,22,10,11,12,32,21,12,41,31,20,21,41,30,32,21,41,31,21,22,-1,30,31,32,-1)

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
        if (current2.value != rabbit.value) {
            pics[rabbit.value].value = R.drawable.rabbit
        } else {
            if (current2.value == rabbit.value) {
                pics[rabbit.value].value = R.drawable.rabbitselect
            }
        }

        fun re(){
            for(item in pics){
            if (item.value == R.drawable.selected) {
                item.value = R.drawable.dot
            }
            if (item.value == R.drawable.dogselect) {
                item.value = R.drawable.dog
            }
            if (item.value == R.drawable.rabbitselect) {
                item.value = R.drawable.rabbit
            }}
        }
        fun indexOf(array:MutableList<Int>,element:Int):Int{
            array.indexOf(element)
            return -1
        }
        var flag = remember{ mutableStateOf(true)}
        fun movement(picNum: Int){

            if(flag.value){ //狗子
                if (current.value != picNum && pics[picNum].value == R.drawable.selected) {
                    when(dogs.indexOf(current.value)){
                        0 -> dog1.value = picNum
                        1 -> dog2.value = picNum
                        2 -> dog3.value = picNum
                    }
                    dogs[dogs.indexOf(current.value)]= picNum

                    pics[current.value].value = R.drawable.dot
                    re()
                    current.value = 50
                    flag.value = false
                } else {
                    if (pics[picNum].value == R.drawable.dog) {
                        current.value = picNum
                        re()
                        (arrD[picNum]until arrD[picNum+1]).forEach {
                            var linkPoint = arrD[it].div(10)*3 + arrD[it].mod(10)
    //                        stack.push(linkPoint)
                            if (dogs.indexOf(linkPoint) < 0 && linkPoint != rabbit.value) {
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
            }else { //兔子
                if (current2.value != picNum && pics[picNum].value == R.drawable.selected) {
                    rabbit.value = picNum
                    pics[current2.value].value = R.drawable.dot
                    re()
                    current2.value = 50
                    flag.value = true
                } else {
                    if (pics[picNum].value == R.drawable.rabbit) {
                        current2.value = picNum
                        re()
                        (arrR[picNum]until arrR[picNum+1]).forEach {
                            var linkPoint = arrR[it].div(10)*3 + arrR[it].mod(10)
                            //                        stack.push(linkPoint)
                            if (dogs.indexOf(linkPoint) < 0 && linkPoint != rabbit.value) {
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
            if((dogs[0]+dogs[1]+dogs[2]).div(10).div(3) < rabbit.value - 1){
                //Rabbit win
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
