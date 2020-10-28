package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.service.vo.ApplicationInstance;
import com.wine.to.up.deployment.service.service.vo.DefaultApplicationInstance;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class DeploymentServiceImpl implements DeploymentService {

    @Override
    public void test() {
        // DO something
    }

    @Override
    public List<ApplicationInstance> getInstances(String serviceId) {
        String hostname = "";
        try {
            List<ApplicationInstance> appInstances = new ArrayList<>();
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            if (addresses != null) {
                for (InetAddress address : addresses) {
                    DefaultApplicationInstance appInstance = new DefaultApplicationInstance(
                            serviceId, address.getHostAddress(), 8080, false);
                    appInstances.add(appInstance);
                }
            }
            return appInstances;
        }
        catch (UnknownHostException e) {
            return Collections.emptyList();
        }
    }
}
