package pe.edu.cibertec.final

object MockAuthManager {
    fun validateCredentials(username: String, password: String): Boolean {
        val normalizedUser = username.trim()
        val normalizedPassword = password.trim()

        return normalizedUser.equals("admin", ignoreCase = true) &&
            normalizedPassword == "1234"
    }
}
