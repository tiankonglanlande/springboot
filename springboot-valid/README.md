## 最近发现这段代码不执行
```
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String, Object> bindExceptionHandler(BindException ex) throws Exception {
        String s  = ex.getFieldError().getField()+":"+ex.getFieldError().getDefaultMessage();
        Map<String,Object> map=new HashMap<>();
        map.put("code",400);
        map.put("msg",s);
        return map;
    }
 ```
 ## 修改为以下即可
 ```
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, Object> bindExceptionHandler(MethodArgumentNotValidException ex) throws Exception {
        BindingResult result = ex.getBindingResult();
       StringBuilder s=new StringBuilder();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                s.append(fieldError.getField()+":"+fieldError.getDefaultMessage());
            });
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",400);
        map.put("msg",s);
        return map;
    }
 ```
 
 
 
    
