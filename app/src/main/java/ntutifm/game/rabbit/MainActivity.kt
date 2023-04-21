package ntutifm.game.rabbit

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ntutifm.game.rabbit.Screen.*
import ntutifm.game.rabbit.ui.theme.RabbitTheme
import ntutifm.game.rabbit.ui.theme.isLight
import kotlin.system.exitProcess

val serviceStatus = mutableStateOf(true)
var isStart = mutableStateOf(true)

class MainActivity : ComponentActivity() {
    fun exitAPP() {
        val activityManager =
            applicationContext?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appTaskList = activityManager.appTasks

        for (i in appTaskList.indices) {
            appTaskList[i].finishAndRemoveTask()
        }
        exitProcess(0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RabbitTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    val context = LocalContext.current
                    if(serviceStatus.value){
                        context.startService(Intent(context, MyService::class.java))
                    }else{
                        context.stopService(Intent(context, MyService::class.java))
                    }

                    rememberSystemUiController().setStatusBarColor(
                        Color.Transparent, darkIcons = MaterialTheme.colorScheme.isLight())
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "Main"
                    ) {
                        composable(route = "Main") {
                           MainScreen(
                                navController = navController,)
                        }
                        composable(route = "PlayTwo") {
                            PlayTwo(
                                navController = navController,)
                        }
                        composable(route = "PlayDog") {
                            PlayDog(
                                navController = navController,)
                        }
                        composable(route = "PlayRabbit") {
                            PlayRabbit(
                                navController = navController,)
                        }
                        composable(route = "Computer") {
                           Computer(
                                navController = navController,)
                        }
                        composable(route = "Setting") {
                            PlayTwoAI(
                                navController = navController,)
                        }
                    }
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && isStart.value) {
            exitAPP()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
