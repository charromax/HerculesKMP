
import androidx.compose.ui.window.ComposeUIViewController
import org.charr0max.herculeskmp.App
import org.charr0max.herculeskmp.cache.Database
import org.charr0max.herculeskmp.cache.IOSDriverFactory

fun MainViewController() {
    val driverFactory = IOSDriverFactory()
    val herkulesDatabase = Database(driverFactory).database
    ComposeUIViewController { App(herkulesDatabase) }
}
