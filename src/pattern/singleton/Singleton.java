package pattern.singleton;

public class Singleton {

    /**
     * 饥饿模式
     * @author Administrator
     *
     */
    public static final class EagerSingleton  
    {  
        private static EagerSingleton singObj = new EagerSingleton();  
      
        private EagerSingleton(){  
        }  
      
        public static EagerSingleton getSingleInstance(){  
           return singObj;
        }  
    } 
    
    /**
     * 懒汉模式
     * 延迟加载（Lazy-load Singleton）
     * @author Administrator
     *
     */
    public static final class LazySingleton  
    {  
        private static LazySingleton singObj = null;  
      
        private LazySingleton(){  
        }  
      
        public static LazySingleton getSingleInstance(){  
            if(null == singObj ) singObj = new LazySingleton();
              return singObj;
        }  
    } 
    
    /**
     * 线程安全
     * @author Administrator
     *
     */
    public static final class ThreadSafeSingleton  
    {  
        private static ThreadSafeSingleton singObj = null;  
      
        private ThreadSafeSingleton(){  
        }  
      
        public static synchronized ThreadSafeSingleton getSingleInstance(){  
            if(null == singObj ) singObj = new ThreadSafeSingleton();
                return singObj;
        }  
    }  
    
    /**
     * 双重检查锁（Double-Checked Lock）。
     * 把同步的粒度降低，只在初始化对象的时候进行同步
     * @author Administrator
     *
     */
    public static final class DoubleCheckedSingleton  
    {  
        // volatile保证所有线程即时看到公共内存的对象
        private static volatile DoubleCheckedSingleton singObj = null;  
      
        private DoubleCheckedSingleton(){  
        }  
      
        public static DoubleCheckedSingleton getSingleInstance(){  
            if(null == singObj ) {
                  synchronized(DoubleCheckedSingleton.class){
                         if(null == singObj)
                               singObj = new DoubleCheckedSingleton();
                  }
             }
           return singObj;
        }  
    }  
    
    /**
     * Initialization on Demand Holder.
     * The nested class is referenced no earlier (and therefore loaded no earlier by the class loader) than the moment that getInstance() is called. 
     * Thus, this solution is thread-safe without requiring special language constructs (i.e. volatile or synchronized).
     * @author Administrator
     *
     */
    public static class SingletonIDH    
    {    
        private SingletonIDH() {}
        private static class SingletonHolder    
        {    
            public final static SingletonIDH instance = new SingletonIDH();    
        }    
       
        public static SingletonIDH getInstance()    
        {    
            return SingletonHolder.instance;    
        }    
    }
    
    public static void main(String[] args) {
		SingletonIDH sidh = SingletonIDH.getInstance();
		System.out.println(sidh);
		sidh = SingletonIDH.getInstance();
		System.out.println(sidh);
	}
}
