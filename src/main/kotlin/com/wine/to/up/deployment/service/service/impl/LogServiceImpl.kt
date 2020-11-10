package com.wine.to.up.deployment.service.service.impl

import com.github.dockerjava.api.async.ResultCallbackTemplate
import com.github.dockerjava.api.model.Frame
import com.wine.to.up.deployment.service.entity.Log
import com.wine.to.up.deployment.service.service.DockerClientFactory
import com.wine.to.up.deployment.service.service.LogService
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class LogServiceImpl(
        val dockerClientFactory: DockerClientFactory
) : LogService {


    override fun logsByInstances(instances: List<ApplicationInstanceVO>): List<Log> {
        val dockerClient = dockerClientFactory.getDockerClient("", "")
        return instances.flatMap {
            val logs = mutableListOf<Log>()
            val syncPoint = CompletableFuture<Boolean>()
            dockerClient.logServiceCmd(it.appId).exec(ResultCallback(logs, syncPoint))
            syncPoint.get()
            logs
        }
    }

    class ResultCallback(
            val resultList: MutableList<Log>,
            val syncPoint: CompletableFuture<Boolean>
    ) : ResultCallbackTemplate<ResultCallback, Frame>() {

        override fun onNext(`object`: Frame?) {
            resultList.add(
                    Log(System.currentTimeMillis(), String(`object`?.payload ?: "Error: no log found".toByteArray()))
            )
        }

        override fun onComplete() {
            super.onComplete()
            syncPoint.complete(true)
        }
    }
}