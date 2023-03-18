package ntutifm.game.rabbit.Screen

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

@Composable
fun MainScreen(navController: NavController) {
    Box {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            ElevatedCard(modifier = Modifier
                .padding(top = 200.dp).clickable { navController.navigate("PlayTwo")  }) {
                Text(text = "雙人遊戲",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp,end = 20.dp, bottom = 15.dp)
                )
            }
            ElevatedCard(modifier = Modifier
                .padding(top = 100.dp).clickable { navController.navigate("Computer") }) {
                Text(text = "與電腦對戰",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp,end = 20.dp, bottom = 15.dp)
                )
            }
            ElevatedCard(modifier = Modifier
                .padding(top = 100.dp).clickable {navController.navigate("Setting")  }) {
                Text(text = "設置",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp,end = 20.dp, bottom = 15.dp)
                )
            }
        }
    }
}