package org.jakot.modules.authmodule.db.services

import org.jakot.modules.authmodule.db.entity.ModuleProperties
import org.jakot.modules.authmodule.db.jpa.ModulePropertiesRepository
import org.springframework.stereotype.Service

@Service
class AppPropertyService(val modulePropertiesRepository: ModulePropertiesRepository) {

    fun getProperty(propertyName: String): String? {
        return modulePropertiesRepository.findOneByName(propertyName)?.value
    }

    fun setProperty(propertyName: String, value: Any) {
        modulePropertiesRepository.save(ModuleProperties(name = propertyName, value = value.toString()))
    }
}