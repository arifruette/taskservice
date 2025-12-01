package domain.model

@JvmInline
value class TaskName(val value: String) {
    init {
        require(value.isNotEmpty() && value.length <= 255) {
            "Name value must not be empty and length must be lower than 255"
        }
    }
}