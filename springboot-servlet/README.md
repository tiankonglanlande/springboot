#### 可能遇到的问题
1.HTTP method GET is not supported by this URL
解决方法：doGet方法中去掉super.doGet()方法调用

#### 2.java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
原因：（1）将req.startAsync()错写成req.getAsyncContext();
      （2）asyncContext.complete()需要在任务完成后调用




