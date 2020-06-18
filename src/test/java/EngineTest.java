import model.Airplane;
import model.Parts.GasTurbineEngine;
import org.junit.Test;

import java.util.Date;

public class EngineTest {

    @Test
    public void engineTest(){
        Airplane airplane = new Airplane();
        GasTurbineEngine engine = GasTurbineEngine.getEngine(airplane,"XS4533SDG", "GE", 0, new Date(),
                1500, 1200, 8240, 230);
        GasTurbineEngine engine2 = GasTurbineEngine.getEngine(airplane,"AW4333SMN", "BMW", 0, new Date(),
                1500, 1200, 8240, 230);

        System.out.println(airplane);
    }
}
