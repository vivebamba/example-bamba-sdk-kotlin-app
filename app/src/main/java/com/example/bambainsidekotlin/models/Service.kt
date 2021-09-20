package com.example.bambainsidekotlin.models

data class Service(var name: String, var renewalDate: String, val isValid: Boolean) {
    companion object {
        private var lastServiceId = 0
        fun createServicesList(servicesQuantity: Int): ArrayList<Service> {
            val services = ArrayList<Service>()
            for (i in 1..servicesQuantity) {
                val service = Service(
                    "Plan " + ++lastServiceId,
                    "14 de Septiembre",
                    i <= servicesQuantity / 2
                )
                services.add(service)
            }
            return services
        }
    }
}
