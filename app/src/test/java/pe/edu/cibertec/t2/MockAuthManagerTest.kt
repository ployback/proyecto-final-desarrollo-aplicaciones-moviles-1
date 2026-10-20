package pe.edu.cibertec.final

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MockAuthManagerTest {

    @Test
    fun `valid credentials should login`() {
        assertTrue(MockAuthManager.validateCredentials("admin", "1234"))
    }

    @Test
    fun `invalid credentials should fail`() {
        assertFalse(MockAuthManager.validateCredentials("admin", "wrong"))
    }
}
