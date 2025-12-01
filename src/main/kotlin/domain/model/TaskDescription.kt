package domain.model

@JvmInline
value class TaskDescription(val value: String) {
    init {
        require(value.isNotEmpty() && value.length <= 255) {
            "Description value must not be empty and length must be lower than 255"
        }
    }
}