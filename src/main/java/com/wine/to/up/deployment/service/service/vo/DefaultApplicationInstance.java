package com.wine.to.up.deployment.service.service.vo;


import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DefaultApplicationInstance implements ApplicationInstance {
    @Getter @Setter private  String appId;

    @Getter @Setter private  int templateId;

    @Getter @Setter private String version;

    @Getter @Setter private int containerId;

    @Getter @Setter private  int dateCreated;

    @Getter @Setter private String userCreated;

    @Getter @Setter private String status;
   // ---------------------------------------

    @Getter @Setter private String instanceId;

    @Getter @Setter private String serviceId;

    @Getter @Setter  private String host;

    @Getter @Setter  private int port;

    @Getter @Setter private Map<String, String> metadata = new LinkedHashMap<>();

    @Getter private URI uri;

    private boolean secure;
    // ---------------------------------------

    /**
     * @param instanceId the id of the instance.
     * @param serviceId the id of the service.
     * @param host the host where the service instance can be found.
     * @param port the port on which the service is running.
     * @param secure indicates whether or not the connection needs to be secure.
     */
    
    public DefaultApplicationInstance(String appId, int templateId, String version, int containerId, int dateCreated, String userCreated, String status,
                                      String instanceId, String serviceId, String host, int port, boolean secure,
                                  Map<String, String> metadata) {
        this.appId = appId;
        this.templateId = templateId;
        this.version = version;
        this.containerId = containerId;
        this.dateCreated = dateCreated;
        this.userCreated = userCreated;
        this.status = status;
        this.instanceId = instanceId;
        this.serviceId = serviceId;
        this.host = host;
        this.port = port;
        this.secure = secure;
        this.metadata = metadata;
    }

    public DefaultApplicationInstance(String serviceId, String hostAddress, int port, boolean secure) {
        this.serviceId = serviceId;
        this.host = hostAddress;
        this.port = port;
        this.secure = secure;
    }


    public static URI getUri(ApplicationInstance instance) {
        String scheme = (instance.isSecure()) ? "https" : "http";
        String uri = String.format("%s://%s:%s", scheme, instance.getHost(), instance.getPort());
        return URI.create(uri);
    }
 /**
    @Override
    public URI getUri() {
        return getUri(this);
    }

    @Override
    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String getInstanceId() {
        return instanceId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }



    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
    **/
    @Override
    public boolean isSecure() {
        return secure;
    }

    public void setUri(URI uri) {
        this.uri = uri;
        this.host = this.uri.getHost();
        this.port = this.uri.getPort();
        String scheme = this.uri.getScheme();
        if ("https".equals(scheme)) {
            this.secure = true;
        }
    }

    @Override
    public String toString() {
        return "DefaultServiceInstance{" + "instanceId='" + instanceId + '\'' + ", serviceId='" + serviceId + '\''
                + ", host='" + host + '\'' + ", port=" + port + ", secure=" + secure + ", metadata=" + metadata + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultApplicationInstance that = (DefaultApplicationInstance) o;
        return port == that.port && secure == that.secure && Objects.equals(instanceId, that.instanceId)
                && Objects.equals(serviceId, that.serviceId) && Objects.equals(host, that.host)
                && Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instanceId, serviceId, host, port, secure, metadata);
    }

}