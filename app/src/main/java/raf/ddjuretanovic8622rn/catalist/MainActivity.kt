package raf.ddjuretanovic8622rn.catalist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import raf.ddjuretanovic8622rn.catalist.navigation.CatNavigation
import raf.ddjuretanovic8622rn.catalist.ui.theme.CatalistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalistTheme {
                CatNavigation()
            }
        }
    }
}