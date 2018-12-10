  @Test
    public void Wa() throws  Exception{
        final  ZooKeeper zooKeeper = new ZooKeeper("192.168.26.134:2181",5000,null);
        Watcher w  = null;
        w = new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                try {
                    System.out.println("数据被修改了");
                    zooKeeper.getData("/b",this,null);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        Stat   stat = new Stat();
        byte[] data = zooKeeper.getData("/b",w,stat);
        System.out.println(new String(data));
        while (true){
            Thread.sleep(1000);
        }
    }

    linux：开启zookeeper服务，
    每次 set /b "新的数据" 时，控制台都会显示“数据被修改了”