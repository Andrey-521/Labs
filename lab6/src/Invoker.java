import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Invoker {

    public void invokeAnnotatedMethods(MyClass instance)
            throws InvocationTargetException, IllegalAccessException {

        // Получаем все методы, объявленные непосредственно в MyClass.
        Method[] methods = MyClass.class.getDeclaredMethods();

        for (Method method : methods) {
            Repeat repeat = method.getAnnotation(Repeat.class);

            if (repeat == null) {
                continue;
            }

            int modifiers = method.getModifiers();
            boolean isProtectedOrPrivate =
                    Modifier.isProtected(modifiers) || Modifier.isPrivate(modifiers);

            if (!isProtectedOrPrivate) {
                continue;
            }

            // Для доступа к protected- и private-методам.
            method.setAccessible(true);

            Object[] arguments = createArguments(method.getParameterTypes());

            for (int i = 0; i < repeat.times(); i++) {
                method.invoke(instance, arguments);
            }
        }
    }

    private Object[] createArguments(Class<?>[] parameterTypes) {
        Object[] arguments = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];

            if (parameterType == String.class) {
                arguments[i] = "Аргумент из Invoker";
            } else if (parameterType == int.class || parameterType == Integer.class) {
                arguments[i] = 42;
            } else {
                throw new IllegalArgumentException(
                        "Неизвестный тип параметра: " + parameterType.getName()
                );
            }
        }

        return arguments;
    }
}
