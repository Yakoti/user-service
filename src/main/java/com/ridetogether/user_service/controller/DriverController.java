package com.ridetogether.user_service.controller;

import com.ridetogether.user_service.dto.DriverDto;
import com.ridetogether.user_service.dto.PassengerDto;
import com.ridetogether.user_service.service.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//TODO: this getbyid when id is of a passenger ? So frontend gotta use the users/id not users/drivers/id
//  "error": "Internal Server Error",
//  "trace": "java.lang.IllegalArgumentException: You are trying to map user that is driver to passenger\r\n\tat com.ridetogether.user_service.mapper.PassengerDtoMapper.toDto(PassengerDtoMapper.java:15)\r\n\tat java.base/java.util.Optional.map(Optional.java:260)\r\n\tat com.ridetogether.user_service.service.PassengerService.findById(PassengerService.java:30)\r\n\tat com.ridetogether.user_service.controller.PassengerController.getPassengerById(PassengerController.java:21)\r\n\tat java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)\r\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:580)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:258)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:191)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:991)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:896)\r\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)\r\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)\r\n\tat org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903)\r\n\tat jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)\r\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)\r\n\tat jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n\tat com.ridetogether.user_service.config.RequestLoggingFilter.doFilterInternal(RequestLoggingFilter.java:19)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)\r\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)\r\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483)\r\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:116)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)\r\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:398)\r\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)\r\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:903)\r\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1769)\r\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1189)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:658)\r\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)\r\n\tat java.base/java.lang.Thread.run(Thread.java:1575)\r\n",
//  "message": "You are trying to map user that is driver to passenger",
@RestController
@RequestMapping("/users/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable long id){
        Optional<DriverDto> driverDto = driverService.findById(id);
        if (driverDto.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(driverDto.get());
    }

    @GetMapping
    public List<DriverDto> getAllDrivers() {
        return driverService.findAllDrivers();
    }

    @GetMapping("/{id}/matching-drivers")
    public List<PassengerDto> findMatchingPassengers(@PathVariable Long id){
            return driverService.findMatchingPassengers(id);
    }

//    @PostMapping
//    public ResponseEntity<DriverDto> createDriver(@RequestBody DriverDto driverDto) {
//        System.out.println(driverDto);
//        return  ResponseEntity.ok(driverService.createDriver(driverDto));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<DriverDto> updateDriver(@PathVariable Long id, @RequestBody DriverDto driverDto) {
//        driverService.updatedriverDto(driverDto);
//        return ResponseEntity.ok(driverService.updatedriverDto(driverDto));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDriver(@RequestBody DriverDto driverDto) {
//        driverDtoService.deleteDriver(driverDto);
//        return ResponseEntity.noContent().build();
//    }
}
