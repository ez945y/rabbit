package ntutifm.game.rabbit.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ntutifm.game.rabbit.R
import ntutifm.game.rabbit.isStart

@Composable
fun MainScreen(navController: NavController) {
    isStart.value = true
    Box {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(id = R.drawable.wolf),null,modifier = Modifier
                .padding(top = LocalConfiguration.current.screenHeightDp.dp / 4).size(100.dp))
            ElevatedCard(modifier = Modifier
                .padding(top = LocalConfiguration.current.screenHeightDp.dp / 9)
                .clickable { navController.navigate("PlayTwo") }) {
                Text(text = "雙人遊戲",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp,end = 20.dp, bottom = 15.dp)
                )
            }
            ElevatedCard(modifier = Modifier
                .padding(top = LocalConfiguration.current.screenHeightDp.dp / 9)
                .clickable { navController.navigate("Computer") }) {
                Text(text = "與電腦對戰",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp,end = 20.dp, bottom = 15.dp)
                )
            }
//            ElevatedCard(modifier = Modifier
//                .padding(top = 100.dp).clickable {navController.navigate("Setting")  }) {
//                Text(text = "設置",
//                    textAlign = TextAlign.Center,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier
//                        .padding(start = 20.dp, top = 15.dp,end = 20.dp, bottom = 15.dp)
//                )
//            }
        }
    }
}