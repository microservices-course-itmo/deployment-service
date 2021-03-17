import com.nhaarman.mockitokotlin2.*
import com.wine.to.up.deployment.service.dao.LogRepository
import com.wine.to.up.deployment.service.entity.ApplicationTemplate
import com.wine.to.up.deployment.service.entity.Log
import com.wine.to.up.deployment.service.service.impl.LogServiceImpl
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit


class LogServiceImplTest {
    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)
    private val mockLogRepository: LogRepository = mock()

    @Rule
    var rule = MockitoJUnit.rule()
/*
        val mockApplicationService: ApplicationService = mock()
        whenever(mockApplicationService.getApplicationTemplate(any(String::class.java))).thenReturn(null)
        }*/

    @MockK
    lateinit var logRepository: LogRepository

    @InjectMockKs
    lateinit var logService: LogServiceImpl


    @Test
fun logsByTemplate() {
    val template = ApplicationTemplate().apply{
        this.baseBranch = ""
        this.createdBy = ""
        this.dateCreated = 1L
        this.description = ""
        this.environmentVariables = null
        this.id = 1L
        this.name = ""
        this.memoryLimits = 1L
        this.portMappings = null
        this.volumes = null
    }

    val log = Log(1L, 1L, null, null, null, 1L)
    val myList = listOf(log)
    whenever(logRepository.findAllByTemplateName(any())).thenReturn(myList)
  /* val manager = LogServiceImpl(mockLogRepository)*/
    verify(mockLogRepository, times(1)).findAllByTemplateName(template.name)
}
}


/*
    @Test
        fun writeLog() {
            val log = Log(1L, 1L, null, null, null, 1L)
            val myList = listOf(log)
            whenever(mockLogRepository.save(any <Log> ())).thenReturn(myList);

            mockLogRepository.save(log)
            verify(mockLogRepository, times (1)).save(log)
        }
    }
*/

/*
val template = ApplicationTemplate().apply{
    this.baseBranch = ""
    this.createdBy = ""
    this.dateCreated = 1L
    this.description = ""
    this.environmentVariables = null
    this.id = 1L
    this.name = ""
    this.memoryLimits = 1L
    this.portMappings = null
    this.volumes = null
}*/