package karate

import com.intuit.karate.junit5.Karate
import org.junit.jupiter.api.Tag

@Tag("e2e-test")
class KarateTests {
    @Karate.Test
    fun testAll(): Karate? {
        return Karate.run().relativeTo(javaClass)
    }
}