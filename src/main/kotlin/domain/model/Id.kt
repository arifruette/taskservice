package domain.model

@JvmInline
value class Id(val value: Long) {
    init {
        require(value >= 0) {
            "Id value must be greater than or equal to 0"
        }
    }
}