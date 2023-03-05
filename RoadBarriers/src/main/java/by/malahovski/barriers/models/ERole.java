package by.malahovski.barriers.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;


public enum ERole {
	@JsonProperty("user")
	@JsonEnumDefaultValue
	ROLE_USER,

	@JsonProperty("manager")
	ROLE_MANAGER,

	@JsonProperty("admin")
	ROLE_ADMIN
}
