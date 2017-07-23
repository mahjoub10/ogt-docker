package com.show.car.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * properties holder for car.
 */
@ConfigurationProperties(prefix = "car", ignoreUnknownFields = false)
public class CarProperties {

    private final Async async = new Async();

    private Swagger swagger = new Swagger();

    private Oauth2 oauth2 = new Oauth2();

    private Email email = new Email();

    public Email getEmail() {
        return email;
    }

    public Async getAsync() {
        return async;
    }

    public Swagger getSwagger() {
        return swagger;
    }

    public Oauth2 getOauth2() {
        return oauth2;
    }

    public static class Async {

        private boolean enabled;

        private int threadPoolSize;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getThreadPoolSize() {
            return threadPoolSize;
        }

        public void setThreadPoolSize(int threadPoolSize) {
            this.threadPoolSize = threadPoolSize;
        }

    }

    public static class Swagger {

        private String title = "Car Show application API";

        private String description = "Car show API documentation";

        private String version = "0.1.0";

        private String termsOfServiceUrl;

        private String contactName;

        private String contactUrl;

        private String contactEmail;

        private String license;

        private String licenseUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }
    }

    public static class Oauth2 {

        private String client_id;

        private String client_secret;

        private String grant_type;

        private String scope;

        private int token_validity;

        private int refresh_token_validity;

        private String jwt_key;


        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        public String getClient_secret() {
            return client_secret;
        }

        public void setClient_secret(String client_secret) {
            this.client_secret = client_secret;
        }

        public String getGrant_type() {
            return grant_type;
        }

        public void setGrant_type(String grant_type) {
            this.grant_type = grant_type;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public int getToken_validity() {
            return token_validity;
        }

        public void setToken_validity(int token_validity) {
            this.token_validity = token_validity;
        }

        public int getRefresh_token_validity() {
            return refresh_token_validity;
        }

        public void setRefresh_token_validity(int refresh_token_validity) {
            this.refresh_token_validity = refresh_token_validity;
        }

        public String getJwt_key() {
            return jwt_key;
        }

        public void setJwt_key(String jwt_key) {
            this.jwt_key = jwt_key;
        }
    }

    public static class Email {

        private String from;

        private String adminEmail;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getAdminEmail() {
            return adminEmail;
        }

        public void setAdminEmail(String adminEmail) {
            this.adminEmail = adminEmail;
        }
    }
}
