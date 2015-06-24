package data.python.PythonEnviroment;

import org.python.core.Py;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

/**
 * Created by chenghao on 15/5/27.
 */
public class PythonInterpreterFactory {
    public static PythonInterpreter getPythonInterpreter(){
        PythonInterpreter interpreter = new PythonInterpreter();
        PySystemState sys = Py.getSystemState();
        interpreter.execfile("python/python_init.py");
        return interpreter;
    }
}
