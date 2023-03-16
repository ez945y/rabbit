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
        val pic1 = remember { mutableStateOf(R.drawable.dog) }
        val pic3 = remember { mutableStateOf(R.drawable.dog) }
        val pic4 = remember { mutableStateOf(R.drawable.selected) }
        val pic5 = remember { mutableStateOf(R.drawable.dog)}
        val pic6 = remember { mutableStateOf(R.drawable.selected) }
        val pic7 = remember { mutableStateOf(R.drawable.selected) }
        val pic8 = remember { mutableStateOf(R.drawable.selected) }
        val pic9 = remember { mutableStateOf(R.drawable.selected) }
        val pic10 = remember { mutableStateOf(R.drawable.selected) }
        val pic11 = remember { mutableStateOf(R.drawable.selected) }
        val pic13 = remember { mutableStateOf(R.drawable.rabbit) }
        //1
        Box(modifier = Modifier
            .padding(top = 614.dp, start = 147.dp)
            .size(80.dp)
            .clickable { pic1.value = R.drawable.dogselected })
        {
            Image(painterResource(id = pic1.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
        //3
        Box(modifier = Modifier
            .padding(top = 485.dp, start = 271.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic3.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
        //4
        Box(modifier = Modifier
            .padding(top = 485.dp, start = 147.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic4.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
        //5
        Box(modifier = Modifier
            .padding(top = 485.dp, start = 24.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic5.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
        //6
        Box(modifier = Modifier
            .padding(top = 353.dp, start = 271.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic6.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
        //7
        Box(modifier = Modifier
            .padding(top = 353.dp, start = 147.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic7.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
        //8
        Box(modifier = Modifier
            .padding(top = 353.dp, start = 24.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic8.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
        //9
        Box(modifier = Modifier
            .padding(top = 228.dp, start = 271.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic9.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
        //10
        Box(modifier = Modifier
            .padding(top = 228.dp, start = 147.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic10.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
        //11
        Box(modifier = Modifier
            .padding(top = 228.dp, start = 24.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic11.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
        //13
        Box(modifier = Modifier
            .padding(top = 90.dp, start = 147.dp)
            .size(80.dp)) {
            Image(painterResource(id = pic13.value),
                null,
                modifier = Modifier.fillMaxSize())
        }
    }


}
