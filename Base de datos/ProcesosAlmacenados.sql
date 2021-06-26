use DBTonysKinal2019036IN5BM;

#-------------------------------------------------------------------TIPO EMPLEADO--------------------------------------------------------
#-----------Proceso Insertar Datos----------
delimiter $$
create procedure sp_AgreagarTipoEmpleado(Descripcion varchar(100))
begin 
insert into TipoEmpleado(DescripcionTipoEmpleado) value
						(Descripcion);
end $$
delimiter ;
#-------Activar Insertar Datos-------
call sp_AgreagarTipoEmpleado("Administrador");
call sp_AgreagarTipoEmpleado("Gerente");
call sp_AgreagarTipoEmpleado("Cosinero");
call sp_AgreagarTipoEmpleado("Mesero");
call sp_AgreagarTipoEmpleado("Organizador");
call sp_AgreagarTipoEmpleado("Sistemas");

#-------Proceso Mostrar Datos--------
delimiter $$
create procedure sp_MostrarTipoEmpleado()
begin
select CodigoTipoEmpleado,DescripcionTipoEmpleado from TipoEmpleado;
end $$
delimiter ;
#-------Activar Mostrar Datos--------
call sp_MostrarTipoEmpleado();

#--------Proceso Actulizar Datos----
delimiter $$
create procedure sp_ActualizarTipoEmpleado(Codigo int,Descripcion varchar(100))
begin
start transaction;
update TipoEmpleado set DescripcionTipoEmpleado=Descripcion 
					where CodigoTipoEmpleado=Codigo;
commit;
end $$
delimiter ;
#------Activar Actulizar Datos---
call sp_ActualizarTipoEmpleado(1,"Admin");

#-----Proceso Eliminar-----------
delimiter $$
create procedure sp_EliminarTipoEmpleado(Codigo int)
begin
start transaction;
delete from TipoEmpleado where CodigoTipoEmpleado=Codigo;
commit;
end $$
delimiter ;
#----Activar Eliminar--------
call sp_EliminarTipoEmpleado(6);

#------proceso buscar---------
delimiter $$
create procedure sp_BuscarTipoEmpleado(Codigo int)
begin
select  CodigoTipoEmpleado,DescripcionTipoEmpleado from TipoEmpleado where CodigoTipoEmpleado=Codigo;
end $$
delimiter ;
#----Activar el proceso buscar----
call sp_BuscarTipoEmpleado(1);
call sp_BuscarTipoEmpleado(2);
call sp_BuscarTipoEmpleado(3);
call sp_BuscarTipoEmpleado(4);
call sp_BuscarTipoEmpleado(5);

#----------------------------------------------------------- Empresa---------------------------------------------------------
#----------- Proceso lista Empresa-----------
delimiter $$
create procedure sp_MostrarListaEmpresa()
begin
select 	CodigoEmpresa,
		NombreEmpresa,
		DireccionEmpresa,
		telefono
        from Empresas;
end $$
delimiter ;
#--------Activardor ListaEmpresa----
call sp_MostrarListaEmpresa();


#-------Proceso Agregar Empresa------------
delimiter $$
create procedure sp_AgregarEmpresa(NombreE varchar(150),
										DireccionE varchar(150),
                                        TelefonoE varchar(10))
begin
insert into Empresas(NombreEmpresa,
					 DireccionEmpresa,
					 telefono) value 
                     (NombreE,
                     DireccionE,
                     TelefonoE);
end $$
delimiter ;
#-------Activador Agregar Empresa-------
call sp_AgregarEmpresa("La sorisa de una flor","2 av 20-02 San Gabriel zona 10 de Mixco","36211013");
call sp_AgregarEmpresa("Juares S.A","zona 2 de Mixco","52527212");
call sp_AgregarEmpresa("Carlos S.A","2 avenida zona 2 de Mixco","44105263");
call sp_AgregarEmpresa("H && J","9 calle zona 4 Cuidad de Guatemala","63524178");
call sp_AgregarEmpresa("Music Pary","9 avenido 20 calle zona 3 Cuidad de Guatemala","85967421");
call sp_AgregarEmpresa("Valles","4 calle zona 10 de Mixco","96857412");
call sp_AgregarEmpresa("Floristeria San Gabriel","4 calle zona 4 de Mixco","10203040");
call sp_AgregarEmpresa("La sonrisa de una Flor","20-02 San Jacinto zona 10 de Mixco","74859695");
call sp_AgregarEmpresa("USAC","11 Av, Cdad. de Guatemala 01012","68597548");
call sp_AgregarEmpresa("EORM","Paraje Concepción, 8A Calle 0-26, Cdad. de Guatemala","10253647");


#-------Proceso Eliminar Empresa----------
delimiter $$
create procedure sp_EliminarEmpresa(Codigo int)
begin
start transaction;
delete from Empresas where CodigoEmpresa=Codigo;
commit;
end $$
delimiter ;
#-------activado de EliminarEmpresa----------
call sp_EliminarEmpresa(6);
call sp_EliminarEmpresa(7);
call sp_EliminarEmpresa(8);
call sp_EliminarEmpresa(9);
call sp_EliminarEmpresa(10);

#------Proceso Actualizar Empresa-----------
delimiter $$
create procedure sp_ActualizarEmpresa(Codigo int, NombreE varchar(150),
									DireccionE varchar(150), TelefonoE varchar(10))
begin
Start transaction;
update Empresas set NombreEmpresa=NombreE,
					DireccionEmpresa=DireccionE,
                    telefono=TelefonoE where
                    CodigoEmpresa=Codigo;
commit;
end $$
delimiter ;
#---------Activar Actualizar Empresa---------
call sp_ActualizarEmpresa(1,"Floristeria San Gabriel","4 calle zona 4 de Mixco","10203040");
call sp_ActualizarEmpresa(2,"La sonrisa de una Flor","20-02 San Jacinto zona 10 de Mixco","74859695");
call sp_ActualizarEmpresa(3,"USAC","11 Av, Cdad. de Guatemala 01012","68597548");
call sp_ActualizarEmpresa(4,"EORM","Paraje Concepción, 8A Calle 0-26, Cdad. de Guatemala","10253647");
call sp_ActualizarEmpresa(5,"Centro Vocacional San José","place15 Calzada Roosevelt, Zona 3, Guatemala, Guatemala","96857445");

#---------proceso activar buscar empresa-------
delimiter $$
create procedure sp_BuscarEmpresa(Codigo int)
begin
select CodigoEmpresa,
		NombreEmpresa,
		DireccionEmpresa,
		telefono from Empresas where CodigoEmpresa=Codigo;
end $$
delimiter ;
#---------activar buscar empres----------
call sp_BuscarEmpresa(2);

#--------------------------------------------------------------------Empleados--------------------------------------------------------------
#--------Proceso insertar empleados----------
delimiter $$
create procedure sp_AgregarDatosEmpleados(NumeroE int, ApellidoE varchar(150), NombreE varchar(150), DireccionE varchar(150),
											TelefonoC varchar(10),GradoC varchar(50), CodigoTipoE int)
begin
insert into Empleados(NumeroEmpleado,ApellidosEmpleado,NombresEmpleado,DireccionEmpleado,TelefonoContacto,GradoCocinero,CodigoTipoEmpleado)
				values (NumeroE,ApellidoE,NombreE,DireccionE,TelefonoC,GradoC,CodigoTipoE);
end $$
delimiter ;
#------Activar insertar empleados-------
call sp_AgregarDatosEmpleados(1,"Calderon","Jeferson","2 a 20 calle zona 2","10203004","Primer Grado",1);
call sp_AgregarDatosEmpleados(1,"Crisphin","Raul","zona 10 de Mixco","63524178","Segundo Grado",2);
call sp_AgregarDatosEmpleados(2,"Canteo","Daniela","2 a 20 calle zona 4","52524163","segundo Grado",2);
call sp_AgregarDatosEmpleados(3,"Marroquin","Luis","zona 2 ciudad de Guatemala","52527212","tercer Grado",2);
call sp_AgregarDatosEmpleados(4,"Juarez","Luis","zona 9 de Mixco","63968574","tercer Grado",3);
call sp_AgregarDatosEmpleados(5,"Ortiz","Carlos","zona 4 cuidad de Guatemala","95867548","tercer Grado",3);
call sp_AgregarDatosEmpleados(6,"Ortega","Miguel","5 calle zona 2 cuidad de Guatemala","96969696","cuarto Grado",4);
call sp_AgregarDatosEmpleados(7,"Crisphin","Carlos","zona 10 de Mixco","65456544","cuarto Grado",4);
call sp_AgregarDatosEmpleados(8,"Canteo","Daniel","9 calle zona 5","96857412","segundo Grado",4);
call sp_AgregarDatosEmpleados(9,"Marroquin","Miguel","zona 4 ciudad de Guatemala","95356212","tercer Grado",5);
call sp_AgregarDatosEmpleados(5,"Juarez","Santizo","zona 9 de Mixco","20202020","tercer Grado",5);
call sp_AgregarDatosEmpleados(6,"Ortiz","Raul","zona 9 cuidad de Guatemala","96857465","tercer Grado",1);

#-------Proceso Eliminar empleado--------
delimiter $$
create procedure sp_EliminarDatosEmpleados(codigo int)
begin
Start transaction;
delete from Empleados where CodigoEmpleado=codigo;
commit;
end $$
delimiter ;
#------Activar Elimnar Empleados--------
call sp_EliminarDatosEmpleados(6);
call sp_EliminarDatosEmpleados(7);
call sp_EliminarDatosEmpleados(8);
call sp_EliminarDatosEmpleados(9);
call sp_EliminarDatosEmpleados(10);

#-----Proceso editar empleados---------
delimiter $$
create procedure sp_ActualizarEmpleados(CodigoE int,NumeroE int, ApellidoE varchar(150), NombreE varchar(150), DireccionE varchar(150),
											TelefonoC varchar(10),GradoC varchar(50))
begin
Start transaction;
update Empleados set NumeroEmpleado=NumeroE,
					 ApellidosEmpleado=ApellidoE,
                     NombresEmpleado=NombreE,
                     DireccionEmpleado=DireccionE,
                     TelefonoContacto=TelefonoC,
                     GradoCocinero=GradoC
                     where CodigoEmpleado=CodigoE;
commit;
end $$
delimiter ;
#------Activar editar empleados-----
call sp_ActualizarEmpleados(1,1,"Calderon","Naomi","2 a 20 calle zona 2","63636336","Primer Grado");
call sp_ActualizarEmpleados(2,1,"Crisphin","Daniela","zona 10 de Mixco","52525252","Segundo Grado");
call sp_ActualizarEmpleados(3,2,"Canteo","Daniela","2 a 20 calle zona 4","41414141","segundo Grado");
call sp_ActualizarEmpleados(4,3,"Marroquin","Ingrid","zona 2 ciudad de Guatemala","10203050","tercer Grado");
call sp_ActualizarEmpleados(5,4,"Juarez","Carla","zona 9 de Mixco","50604010","tercer Grado");

#------proceso Listar empleados-------
delimiter $$
create procedure sp_MostrarListaEmpleados()
begin
select CodigoEmpleado,NumeroEmpleado,ApellidosEmpleado,NombresEmpleado,DireccionEmpleado,TelefonoContacto,GradoCocinero,CodigoTipoEmpleado
		from Empleados;
end $$
delimiter ;
#----Activar listar empleados------
call sp_MostrarListaEmpleados();

#------------BUSCAR EMPLEADOS
delimiter $$
create procedure sp_BuscarEmpleados(codigo int)
begin
select CodigoEmpleado,NumeroEmpleado,ApellidosEmpleado,NombresEmpleado,DireccionEmpleado,TelefonoContacto,GradoCocinero,CodigoTipoEmpleado
		from Empleados where CodigoEmpleado = codigo;
end $$
delimiter ;
#----Activar BUSCAR empleados------
call sp_BuscarEmpleados(2);

#-------------------------------------------------------------Servicios-------------------------------------------------------------
#-----------Proceso Insertar Datos------------
delimiter $$
create procedure sp_AgregarServicio(fecha date, tipos varchar(100), hora time, lugar varchar(100), telefono varchar(10), codigoe int)
begin
insert into Servicios(FechaServicios,TipoServicio,HoraServicio,LugarServicio,TelefonoContacto,CodigoEmpresa)
				values(fecha,tipos,hora,lugar,telefono,codigoe);
end $$
delimiter ;
#--------Activar insetar datos----------
call sp_AgregarServicio("2020/06/10","Desayuno","07:00:00","Parque de la industria","10121314",1);
call sp_AgregarServicio("2020/06/10","Almuerso","01:00:00","Parque de la industria","10121314",1);
call sp_AgregarServicio("2020/06/12","Desatuno","07:00:00","C.E.V.S.J","52624248",2);
call sp_AgregarServicio("2020/06/13","Desatuno","07:00:00","C.E.V.S.J","52624248",2);
call sp_AgregarServicio("2020/06/14","Desayuno","07:00:00","Parque de la industria","63589412",3);
call sp_AgregarServicio("2020/06/14","Almuerso","01:00:00","Parque de la industria","96857421",1);
call sp_AgregarServicio("2020/06/15","Desatuno","07:00:00","Lodecoid","12321232",4);
call sp_AgregarServicio("2020/06/16","Refaccion tarde","03:00:00","Lodecoid","52624248",4);
call sp_AgregarServicio("2020/06/18","Refaccion Mañana","00:00:00","E.O.R.M 845 J.M","52527212",5);
call sp_AgregarServicio("2020/06/18","Refaccion Tarde","03:00:00","E.O.R.M 845 J.M","52721232",5);

#------------Proceso para eliminar------
delimiter $$
create procedure sp_EliminarDatosServicios(codigo int)
begin
Start transaction;
delete from Servicios where CodigoServicios=codigo;
commit;
end $$
delimiter ;
#------Activar Elimnar Empleados--------
call sp_EliminarDatosServicios(6);
call sp_EliminarDatosServicios(7);
call sp_EliminarDatosServicios(8);
call sp_EliminarDatosServicios(9);
call sp_EliminarDatosServicios(10);


#--------Editar Servicio---------
delimiter $$
create procedure sp_ActualizarServicio(codigoe int,fecha date, tipos varchar(100), hora time, lugar varchar(100), telefono varchar(10))
begin
Start transaction;
update Servicios  set FechaServicios=fecha,
					TipoServicio=tipos,
                    HoraServicio=hora,
                    LugarServicio=lugar,
                    TelefonoContacto=telefono
				where CodigoServicios=codigoe;
commit;
end $$
delimiter ;

#------activar editar servicio---------------
call sp_ActualizarServicio(1,"2020/06/25","Almuerzo","01:00:00","zona 10","63695852");
call sp_ActualizarServicio(2,"2020/06/26","Desayuno","07:00:00","zona 13","53615851");
call sp_ActualizarServicio(3,"2020/06/27","Almuerzo","01:00:00","zona 15","52341789");
call sp_ActualizarServicio(4,"2020/06/28","Cena","08:00:00","zona 16","96969696");
call sp_ActualizarServicio(5,"2020/06/29","Desayuno","07:30:00","zona 1","526341477");

#---------proceso Mostrar Lista servicio-------
delimiter $$
create procedure sp_MostrarListaServicio()
begin
select CodigoServicios,FechaServicios,TipoServicio,HoraServicio,LugarServicio,TelefonoContacto,CodigoEmpresa from Servicios;
end $$
delimiter ;
#--------Activar Mostrar lista de servicios--------
call sp_MostrarListaServicio();

#-------------BUSCAR SERVICIO--------------------
delimiter $$
create procedure sp_BuscarServicio(Codigo int)
begin
select CodigoServicios,FechaServicios,TipoServicio,HoraServicio,LugarServicio,TelefonoContacto,CodigoEmpresa from Servicios where CodigoServicios=Codigo;
end $$
delimiter ;
#---------activar buscar SERVICIO----------
call sp_BuscarServicio(2);

#------------------------------------------------Tipo Platos--------------------------------------------------------------------------
#----------Agregar Tipo Plato----------
delimiter $$
create procedure sp_AgregarTipoPlato(DescripcionTP varchar(100))
begin
insert into TipoPlato(DescripcionTipoPlato) value (DescripcionTp);
end $$
delimiter ;
#----------Activar el procesos agregar plato-------
call sp_AgregarTipoPlato("Español");
call sp_AgregarTipoPlato("Clasico");
call sp_AgregarTipoPlato("Europeo");
call sp_AgregarTipoPlato("Mexicano");
call sp_AgregarTipoPlato("Tradicional");
call sp_AgregarTipoPlato("Comida tradicional de México");
call sp_AgregarTipoPlato("Especialidades");
call sp_AgregarTipoPlato("Postres");

#-----------Actualizar tipo Plato------------
delimiter $$
create procedure sp_ActualizarTipoPlato(codigotp int, descripciontp varchar(100))
begin
Start transaction;
update TipoPlato set DescripcionTipoPlato=descripciontp where CodigoTipoPlato=codigotp;
commit;
end $$
delimiter ;
#-----------Activar actualizar tipo plato--------
call sp_ActualizarTipoPlato(1,"Comida Española");
call sp_ActualizarTipoPlato(3, "Comida Europea");
call sp_ActualizarTipoPlato(4, "comida tradicional");
call sp_ActualizarTipoPlato(5, "Nuestras especialidades");
call sp_ActualizarTipoPlato(6, "pasteles");

#-----------------Mostrar tipos de platos-----------
delimiter $$
create procedure sp_MostrarListaTipoPlato()
begin
select CodigoTipoPlato, DescripcionTipoPlato from TipoPlato;
end $$
delimiter ;
#------------activar mostrar tipo platos------------
call sp_MostrarListaTipoPlato();

#----------------Buscar tipos de platos----------
delimiter $$
create procedure sp_BuscarTipoPlato(codigotp int)
begin
select CodigoTipoPlato, DescripcionTipoPlato from TipoPlato where CodigoTipoPlato=codigotp;
end $$
delimiter ;
#---------activar buscar tipo plato------------
call sp_BuscarTipoPlato(8);

#-------------------eliminar un tipo plato----------
delimiter $$
create procedure sp_EliminarTipoPlato(Codigotp int)
begin
Start transaction;
delete from TipoPlato where CodigoTipoPlato=codigotp;
commit;
end $$
delimiter ;
#--------------eliminar tipo plato----------------
call sp_EliminarTipoPlato(8);

#--------------------------------------------------------- Productos-------------------------------------------------------------------
#----------------proceso de un producto--------------------------------
delimiter $$
create procedure  sp_AgregarProductos(Nombre varchar(150), cantidad int)
begin
insert into Producto(NombreProducto,Cantidad) value (Nombre,cantidad);
end $$
delimiter ;
#------------activar productos-------------------------------------
call sp_AgregarProductos("Palomitas con mantequilla",1);
call sp_AgregarProductos("Paella de mariscos",1);
call sp_AgregarProductos("Som tam",1);
call sp_AgregarProductos("Arroz con pollo",1);
call sp_AgregarProductos("Poutine",1);
call sp_AgregarProductos("Tacos",1);
call sp_AgregarProductos("Pan tostado con mantequilla y Marmite",1);

#-------------Eliminar un producto----------------------------------
delimiter $$
create procedure sp_EliminarProducto(codigo int)
begin
Start transaction;
delete from Producto where CodigoProducto=codigo;
commit;
end $$
delimiter ;
#----------activar-------------------------------------------
call sp_EliminarProducto(7);

#-------------proceso actualizar producto---------------------------
delimiter $$
create procedure sp_ActualizarProducto(codigo int, nombre varchar(150), cantidad int)
begin 
Start transaction;
update Producto set NombreProducto=nombre,
					Cantidad=cantidad
                    where CodigoProducto=codigo;
commit;
end $$
delimiter ;
#------------proceso actualizar producto----------------
call sp_ActualizarProducto(1,"bollos",2);
call sp_ActualizarProducto(3,"Tofu apestoso",1);
call sp_ActualizarProducto(4,"Pan francés",1);
call sp_ActualizarProducto(5,"Helado de chocolate",1);
call sp_ActualizarProducto(6,"Pais de manzana",1);

#--------------------------------buscar producto-----------------------
delimiter $$
create procedure sp_BuscarProducto(codigo int)
begin
select CodigoProducto,NombreProducto,Cantidad from Producto where CodigoProducto=codigo;
end $$
delimiter ;
#--------------activar producto----------------------
call sp_BuscarProducto(1);

#-------------------------mostrar lista de productos--------------
delimiter $$
create procedure sp_MostrarProductos()
begin
select CodigoProducto,NombreProducto,Cantidad from Producto;
end $$
delimiter ;
#------------------activacion--------------------------
call sp_MostrarProductos();


#----------------------------------------------------------Presupuesto-----------------------------------------------------------------
#-------------------agregar datos a presupuesto-----------------
delimiter $$
create procedure sp_AgregarPresupuesto(Fecha date, cantidad decimal(10,2), codigoE int)
begin
insert into Presupuesto(FechaSolicitud,CantidadPresupuesto,CodigoEmpresa) value 
						(Fecha,cantidad,codigoE);
end $$
delimiter ;
#----------------activar------------------------------------
call sp_AgregarPresupuesto("2019/06/10",1000.20,1);
call sp_AgregarPresupuesto("2019/06/11",2000.20,2);
call sp_AgregarPresupuesto("2019/06/13",1200.20,3);
call sp_AgregarPresupuesto("2019/07/18",1300.20,1);
call sp_AgregarPresupuesto("2019/07/19",1500.20,3);
call sp_AgregarPresupuesto("2019/07/22",1000.20,2);

#-----------------actualizar presupuesto-------------------
delimiter $$
create procedure sp_ActualizarPresupuesto(codigop int, Fecha date, cantidad decimal(10,2))
begin
Start transaction;
update Presupuesto set FechaSolicitud=Fecha,
						CantidadPresupuesto=cantidad
                        where CodigoPresupuesto=codigop;
commit;
end $$
delimiter ;
#-----------------------activar-------------------------
call sp_ActualizarPresupuesto(1,"2019/08/10",5300.00);
call sp_ActualizarPresupuesto(2,"2019/08/10",1300.00);
call sp_ActualizarPresupuesto(3,"2019/08/10",1000.00);
call sp_ActualizarPresupuesto(4,"2019/08/10",1250.00);
call sp_ActualizarPresupuesto(5,"2019/08/10",50.00);

#----------------------eliminar un proceso -------------------------
delimiter $$
create procedure sp_EliminarPresupuesto(codigo int)
begin
Start transaction;
delete from Presupuesto where CodigoPresupuesto=codigo;
commit;
end $$
delimiter ;
#--------------------------activar--------------------------
call sp_EliminarPresupuesto(6);

#-----------------------------mostrar presupuesto------------
delimiter $$
create procedure sp_MostrarPresupuesto()
begin
select CodigoPresupuesto,FechaSolicitud,CantidadPresupuesto,CodigoEmpresa from Presupuesto;
end $$
delimiter ;
#--------------------activador mostrar presupuesto------------
call sp_MostrarPresupuesto();

#-----------------------------------------------------------Platos--------------------------------------------------------------------
#-------------------proceso agregar
delimiter $$
create procedure sp_AgregarPlatos(Cantidadp int,nombrep varchar(50), descripcionp varchar(150), preciop decimal(10,2), codigoc int, codigotp int)
begin
insert into Platos(Cantidad,NombreaPlato,DescripcionPlato,PrecioPlato,CodigoCocinero,CodigoTipoPlato)
			value(Cantidadp,nombrep,descripcionp,preciop,codigoc,codigotp);
end $$
delimiter ;
#-------------------activacion del proceso
call sp_AgregarPlatos(1,"Bollos Guatemaltecos","Bollos de pan",2.50,1,1);
call sp_AgregarPlatos(1,"Tamales Guatemaltecos","Tamales de arroz",3.50,3,2);
call sp_AgregarPlatos(1,"Sebiche","Sebiche de camarones",5.00,1,1);
call sp_AgregarPlatos(1,"Sopa de pollo","sopa de pollo",10.00,1,7);
call sp_AgregarPlatos(1,"Pastel de Manzana","Pastel Dulce",5.00,2,7);
call sp_AgregarPlatos(1,"Pastel de Piña","Pastel Dulce",5.00,3,6);

#--------------proceso actualizar
delimiter $$
create procedure sp_ActualizarPlatos(codigo int, cantidadp int, nombrep varchar(50), descripcionp varchar(150), preciop decimal(10,2), codigoc int)
begin
Start transaction;
update Platos set	cantidad=cantidadp,
					NombreaPlato=nombrep,
                    DescripcionPlato=descripcionp,
                    PrecioPlato=preciop,
                    CodigoCocinero=codigoc
                    where CodigoPlato=codigo;
commit;
end $$
delimiter ;
#-------------activar el proceso
call sp_ActualizarPlatos(1,1,"Tamales Guatemaltecos","Tamales de arroz",3.50,3);
call sp_ActualizarPlatos(2,1,"Sebiche","Sebiche de camarones",5.00,1);
call sp_ActualizarPlatos(3,1,"Sopa de pollo","sopa de pollo",10.00,1);
call sp_ActualizarPlatos(4,1,"Pastel de Manzana","Pastel Dulce",5.00,2);
call sp_ActualizarPlatos(5,1,"Pastel de Piña","Pastel Dulce",5.00,3);

#-----------------proceso eliminar
delimiter $$
create procedure sp_EliminarPlato(codigo int)
begin
Start transaction;
delete from Platos where CodigoPlato=codigo;
commit;
end $$
delimiter ;
#-------------activacion del proceso
call sp_EliminarPlato(6);

<#----------------proceso Mostrar lista de platos-------------
delimiter $$
create procedure sp_MostrarPlatos()
begin
select CodigoPlato,Cantidad,NombreaPlato,DescripcionPlato,PrecioPlato,CodigoCocinero,CodigoTipoPlato from Platos;
end $$
delimiter ;
#--------------activacion del proceso----------------
call sp_MostrarPlatos();

#--------------------proceso buscar plato
delimiter $$
create procedure sp_BuscarPlato(codigo int)
begin
select CodigoPlato,Cantidad,NombreaPlato,DescripcionPlato,PrecioPlato,CodigoCocinero,CodigoTipoPlato from Platos where CodigoPlato=codigo;
end $$
delimiter ;
#-------------------activacion del proceso
call sp_BuscarPlato(5);

#-----------------------------------------------productos has platos-----------------------------------------------------------------
#----------------agregar productos has platos
delimiter $$
create procedure sp_AgregarProductos_Has_Platos(productoc int,platosc int)
begin
insert into Productos_has_Platos(Producto_CodigoProducto,Platos_CodigoPlatos)
							value(productoc,platosc);
end $$
delimiter ;
#------------------activar productos has platos
call sp_AgregarProductos_Has_Platos(1,1);
call sp_AgregarProductos_Has_Platos(2,1);
call sp_AgregarProductos_Has_Platos(2,2);
call sp_AgregarProductos_Has_Platos(3,1);
call sp_AgregarProductos_Has_Platos(2,3);

#--------------------Mostrar Productos has platos
delimiter $$
create procedure sp_MostrarProductos_has_Platos()
begin
select Producto_CodigoProducto,Platos_CodigoPlatos from Productos_has_Platos;
end $$
delimiter ;
#-----------------------activar el proceso-------
call sp_MostrarProductos_has_Platos();

#--------------------------------------------------------servicios has platos--------------------------------------------------
#---------------proceso agregar productos
delimiter $$
create procedure sp_AgregarServicios_has_Platos(sc int, pc int)
begin
insert into Servicios_Has_Platos(Servicios_CodigoServicio,Platos_CodigoPlatos)
							value(sc, pc);
end $$
delimiter ;
#---------------activar el proceso---
call sp_AgregarServicios_has_Platos(1,1);
call sp_AgregarServicios_has_Platos(1,2);
call sp_AgregarServicios_has_Platos(2,2);
call sp_AgregarServicios_has_Platos(2,3);
call sp_AgregarServicios_has_Platos(3,3);
call sp_AgregarServicios_has_Platos(5,5);

#------------proceso para mostar los datos-----------
delimiter $$
create procedure sp_MostrarServicios_Has_Platos()
begin
select Servicios_CodigoServicio,Platos_CodigoPlatos from servicios_has_platos;
end $$
delimiter ;
#------------activar el proceso--------
call sp_MostrarServicios_Has_Platos();

#------------------------------------------Servicios has empleados--------------------------------------------------
#-------------------------proceso agregar----------
delimiter $$
create procedure sp_AgregarServiciosHasEmpleados(sc int,ec int, fecha date, hora time, lugar varchar(150))
begin
insert into Servicios_has_Empleados(Servicios_CodigoServicio,Empleado_CodigoEmpleado,FechaEvento,HoraEvento,LugarEvento)
							value(sc,ec,fecha,hora,lugar);
end $$
delimiter ;
#----------------activar el proceso agregar
call sp_AgregarServiciosHasEmpleados(1,1,"2019/06/01","10:00:00","Ciudad de Guatemala");
call sp_AgregarServiciosHasEmpleados(2,2,"2019/06/05","10:30:00","Zona 10");
call sp_AgregarServiciosHasEmpleados(1,2,"2019/08/01","1:00:00","zona 7");
call sp_AgregarServiciosHasEmpleados(2,3,"2019/08/15","2:00:00","Mixco");
call sp_AgregarServiciosHasEmpleados(3,3,"2019/09/10","7:00:00","Mixco");
call sp_AgregarServiciosHasEmpleados(3,2,"2019/10/22","8:00:00","zona 4");

#-------------------------proceso actualizar----------
delimiter $$
create procedure sp_ActualizarServiciosHasEmpleados(sc int,ec int, fecha date, hora time, lugar varchar(150))
begin
Start transaction;
update Servicios_has_Empleados set FechaEvento=fecha,
									HoraEvento=hora,
                                    LugarEvento=lugar
                                    where Servicios_CodigoServicio= sc and
											Empleado_CodigoEmpleado = ec;
commit;
end $$
delimiter ;
#----------------------activar el proceso--------
call sp_ActualizarServiicosHasEmpleados(1,1,"2019/06/02","11:00:00","zona 4");
call sp_ActualizarServiciosHasEmpleados(2,2,"2019/06/02","11:00:00","zona 10");
call sp_ActualizarServiicosHasEmpleados(1,2,"2019/06/02","11:00:00","zona 11");
call sp_ActualizarServiciosHasEmpleados(2,3,"2019/06/02","11:00:00","zona 18");
call sp_ActualizarServiciosHasEmpleados(3,3,"2019/06/02","11:00:00","zona 4");

#--------------------proceso eliminar--------
delimiter $$
create procedure sp_EliminarServiciosHasEmpleados(sc int, ec int)
begin
Start transaction;
delete from Servicios_has_Empleados where Servicios_CodigoServicio= sc and
											Empleado_CodigoEmpleado = ec;
commit;
end $$
delimiter ;
#-------------------activar el proceso 
call sp_EliminarServiciosHasEmpleados(3,2);

#---------------------proceso mostrar---------
delimiter $$
create procedure sp_MostraServiciosHasEmpleados()
begin
select Servicios_CodigoServicio,Empleado_CodigoEmpleado,FechaEvento,HoraEvento,LugarEvento from Servicios_has_Empleados;
end $$
delimiter ;
#-----------------activar el proceso---------
call sp_MostraServiciosHasEmpleados(); 