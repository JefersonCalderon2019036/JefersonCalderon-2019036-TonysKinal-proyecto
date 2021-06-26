create database DBTonysKinal2019036IN5BM;
use DBTonysKinal2019036IN5BM;

#-------------------Tabla Empresa------------------
create table Empresas(CodigoEmpresa int not null primary key auto_increment,
NombreEmpresa varchar(150) not null,
DireccionEmpresa varchar(150) not null,
telefono varchar(10) not null);

#-----------------Tabla Tipo Empleado-------------
create table TipoEmpleado(CodigoTipoEmpleado int not null primary key auto_increment,
DescripcionTipoEmpleado varchar(100) not null );

#---------------Tabla Presupuesto----------------
create table Presupuesto(CodigoPresupuesto int not null primary key auto_increment,
FechaSolicitud date not null,
CantidadPresupuesto decimal(10,2) not null,
CodigoEmpresa int not null,
foreign key (CodigoEmpresa) references Empresas(CodigoEmpresa));

#---------------Tabla Servicios--------------
create table Servicios(CodigoServicios int not null primary key auto_increment,
FechaServicios date not null,
TipoServicio Varchar(100) not null,
HoraServicio time not null,
LugarServicio varchar(100) not null,
TelefonoContacto varchar(100) not null,
CodigoEmpresa int not null,
foreign key (CodigoEmpresa) references Empresas(CodigoEmpresa));

#-------------Tabla Empleados---------------
create table Empleados(CodigoEmpleado int not null primary key auto_increment,
NumeroEmpleado int not null,
ApellidosEmpleado varchar(150) not null,
NombresEmpleado varchar(150) not null,
DireccionEmpleado varchar(150) not null,
TelefonoContacto varchar(10) not null,
GradoCocinero varchar(50),
CodigoTipoEmpleado int not null,
foreign key (CodigoTipoEmpleado) references TipoEmpleado(CodigoTipoEmpleado));

#-----------Tabla Servicios has Empleados-------------
create table Servicios_has_Empleados(Servicios_CodigoServicio int not null,
Empleado_CodigoEmpleado int not null,
FechaEvento date not null,
HoraEvento time not null,
LugarEvento varchar(150) not null,
foreign key (Empleado_CodigoEmpleado) references Empleados(CodigoEmpleado),
foreign key (Servicios_CodigoServicio) references Servicios(CodigoServicios));

#--------------Tabla Tipo Platos---------------
Create table TipoPlato(CodigoTipoPlato int not null primary key auto_increment,
DescripcionTipoPlato varchar(100));

#--------------Tabla Platos-------------------
create table Platos(CodigoPlato int not null primary key auto_increment,
Cantidad int not null,
NombreaPlato varchar(50) not null,
DescripcionPlato varchar(150) not null,
PrecioPlato decimal(10,2) not null,
CodigoCocinero int not null,
CodigoTipoPlato int not null,
foreign key (CodigoTipoPlato) references TipoPlato(CodigoTipoPlato));

#------------Tabla Servicios has platos--------
create table Servicios_has_Platos(Servicios_CodigoServicio int not null,
Platos_CodigoPlatos int not null,
foreign key (Servicios_CodigoServicio) references Servicios(CodigoServicios),
foreign key (Platos_CodigoPlatos) references Platos(CodigoPlato));

#-------------Tabla Producto---------------
create table Producto(CodigoProducto int not null primary key auto_increment,
NombreProducto varchar(150) not null,
Cantidad int not null);

#--------Tabla productos has platos---------
create table Productos_has_Platos(Producto_CodigoProducto int not null,
Platos_CodigoPlatos int not null,
foreign key (Producto_CodigoProducto) references Producto(CodigoProducto),
foreign key (Platos_CodigoPlatos) references Platos(CodigoPlato));
