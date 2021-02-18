import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ hiLoTest.class, puBancoTest.class, BankerTest.class, PlayerTest.class,TableTest.class,fileManagerTest.class })
public class AllTests {}
