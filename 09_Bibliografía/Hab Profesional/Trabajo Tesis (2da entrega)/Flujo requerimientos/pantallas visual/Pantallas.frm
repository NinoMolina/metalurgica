VERSION 5.00
Begin VB.Form NuevoPedidoMayorista 
   Caption         =   "Nuevo Pedido Mayorista"
   ClientHeight    =   8385
   ClientLeft      =   120
   ClientTop       =   420
   ClientWidth     =   10770
   LinkTopic       =   "Form1"
   ScaleHeight     =   8385
   ScaleWidth      =   10770
   StartUpPosition =   3  'Windows Default
   Begin VB.ComboBox Combo2 
      Height          =   315
      Left            =   4920
      TabIndex        =   41
      Text            =   "Efectivo"
      Top             =   7440
      Width           =   1215
   End
   Begin VB.TextBox Text4 
      Height          =   285
      Left            =   8760
      TabIndex        =   39
      Top             =   7440
      Width           =   1335
   End
   Begin VB.TextBox Text3 
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   -1  'True
         Strikethrough   =   0   'False
      EndProperty
      Height          =   285
      Left            =   4800
      TabIndex        =   37
      Text            =   "ingrese cantidad"
      Top             =   3960
      Width           =   1335
   End
   Begin VB.CommandButton Command3 
      Caption         =   "Agregar a Pedido"
      Height          =   375
      Left            =   6840
      TabIndex        =   36
      Top             =   3840
      Width           =   1575
   End
   Begin VB.ComboBox Combo1 
      Height          =   315
      Left            =   1920
      TabIndex        =   34
      Text            =   "Entero"
      Top             =   3960
      Width           =   1335
   End
   Begin VB.Frame Frame3 
      Caption         =   "Detalle Pedido"
      Height          =   2895
      Left            =   240
      TabIndex        =   32
      Top             =   4440
      Width           =   10215
   End
   Begin VB.Frame Frame1 
      Caption         =   "Datos Cliente"
      Height          =   3255
      Left            =   240
      TabIndex        =   0
      Top             =   480
      Width           =   10215
      Begin VB.CommandButton Command2 
         Caption         =   "Registrar Nuevo Cliente"
         Height          =   375
         Left            =   7560
         TabIndex        =   11
         Top             =   840
         Width           =   1815
      End
      Begin VB.Frame Frame2 
         Caption         =   "Cliente"
         Height          =   1575
         Left            =   480
         TabIndex        =   6
         Top             =   1440
         Width           =   9375
         Begin VB.Label Label26 
            Caption         =   "Domicilio:"
            Height          =   255
            Left            =   120
            TabIndex        =   31
            Top             =   960
            Width           =   735
         End
         Begin VB.Label Label14 
            Caption         =   "Calle:"
            Height          =   255
            Left            =   480
            TabIndex        =   30
            Top             =   1200
            Width           =   495
         End
         Begin VB.Label Label15 
            Caption         =   "Lope de Vega"
            BeginProperty Font 
               Name            =   "MS Sans Serif"
               Size            =   8.25
               Charset         =   0
               Weight          =   700
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   960
            TabIndex        =   29
            Top             =   1200
            Width           =   1335
         End
         Begin VB.Label Label16 
            Caption         =   "Nro:"
            Height          =   255
            Left            =   2400
            TabIndex        =   28
            Top             =   1200
            Width           =   495
         End
         Begin VB.Label Label17 
            Caption         =   "Piso:"
            Height          =   255
            Left            =   3480
            TabIndex        =   27
            Top             =   1200
            Width           =   495
         End
         Begin VB.Label Label18 
            Caption         =   "202"
            BeginProperty Font 
               Name            =   "MS Sans Serif"
               Size            =   8.25
               Charset         =   0
               Weight          =   700
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   2880
            TabIndex        =   26
            Top             =   1200
            Width           =   495
         End
         Begin VB.Label Label19 
            Caption         =   "-"
            Height          =   255
            Left            =   3960
            TabIndex        =   25
            Top             =   1200
            Width           =   375
         End
         Begin VB.Label Label20 
            Caption         =   "Depto:"
            Height          =   255
            Left            =   4320
            TabIndex        =   24
            Top             =   1200
            Width           =   615
         End
         Begin VB.Label Label21 
            Caption         =   "-"
            Height          =   255
            Left            =   4920
            TabIndex        =   23
            Top             =   1200
            Width           =   255
         End
         Begin VB.Label Label22 
            Caption         =   "Barrio:"
            Height          =   255
            Left            =   5280
            TabIndex        =   22
            Top             =   1200
            Width           =   615
         End
         Begin VB.Label Label23 
            Caption         =   "Alta Córdoba"
            BeginProperty Font 
               Name            =   "MS Sans Serif"
               Size            =   8.25
               Charset         =   0
               Weight          =   700
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   5880
            TabIndex        =   21
            Top             =   1200
            Width           =   1215
         End
         Begin VB.Label Label24 
            Caption         =   "Localidad:"
            Height          =   255
            Left            =   7320
            TabIndex        =   20
            Top             =   1200
            Width           =   855
         End
         Begin VB.Label Label25 
            Caption         =   "Capital"
            BeginProperty Font 
               Name            =   "MS Sans Serif"
               Size            =   8.25
               Charset         =   0
               Weight          =   700
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   8160
            TabIndex        =   19
            Top             =   1200
            Width           =   855
         End
         Begin VB.Label Label13 
            Caption         =   "26/07/07"
            BeginProperty Font 
               Name            =   "MS Sans Serif"
               Size            =   8.25
               Charset         =   0
               Weight          =   700
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   4080
            TabIndex        =   18
            Top             =   600
            Width           =   1335
         End
         Begin VB.Label Label12 
            Caption         =   "Fecha Alta:"
            Height          =   255
            Left            =   3120
            TabIndex        =   17
            Top             =   600
            Width           =   1095
         End
         Begin VB.Label Label11 
            Caption         =   "Responsable Inscripto"
            BeginProperty Font 
               Name            =   "MS Sans Serif"
               Size            =   8.25
               Charset         =   0
               Weight          =   700
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   5880
            TabIndex        =   16
            Top             =   240
            Width           =   1935
         End
         Begin VB.Label Label10 
            Caption         =   "Condicion de IVA:"
            Height          =   255
            Left            =   4440
            TabIndex        =   15
            Top             =   240
            Width           =   1335
         End
         Begin VB.Label Label9 
            Caption         =   "351-4465433"
            BeginProperty Font 
               Name            =   "MS Sans Serif"
               Size            =   8.25
               Charset         =   0
               Weight          =   700
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   1080
            TabIndex        =   14
            Top             =   600
            Width           =   1335
         End
         Begin VB.Label Label8 
            Caption         =   "Telefono:"
            Height          =   255
            Left            =   240
            TabIndex        =   13
            Top             =   600
            Width           =   855
         End
         Begin VB.Label Label6 
            Caption         =   "Super Uno"
            BeginProperty Font 
               Name            =   "MS Sans Serif"
               Size            =   8.25
               Charset         =   0
               Weight          =   700
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   3120
            TabIndex        =   10
            Top             =   240
            Width           =   975
         End
         Begin VB.Label Label5 
            Caption         =   "Razon Social:"
            Height          =   255
            Left            =   1920
            TabIndex        =   9
            Top             =   240
            Width           =   1095
         End
         Begin VB.Label Label4 
            Caption         =   "26"
            BeginProperty Font 
               Name            =   "MS Sans Serif"
               Size            =   8.25
               Charset         =   0
               Weight          =   700
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   1200
            TabIndex        =   8
            Top             =   240
            Width           =   495
         End
         Begin VB.Label Label3 
            Caption         =   "ID Cliente:"
            Height          =   255
            Left            =   240
            TabIndex        =   7
            Top             =   240
            Width           =   735
         End
      End
      Begin VB.TextBox Text1 
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   8.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Height          =   285
         Index           =   1
         Left            =   5280
         TabIndex        =   3
         Text            =   " ingrese razon social"
         Top             =   360
         Width           =   1695
      End
      Begin VB.TextBox Text2 
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   8.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Height          =   285
         Index           =   1
         Left            =   1320
         TabIndex        =   2
         Text            =   "  ingrese id cliente"
         Top             =   360
         Width           =   1695
      End
      Begin VB.CommandButton Command1 
         Caption         =   "Buscar Cliente"
         Height          =   375
         Index           =   1
         Left            =   7560
         TabIndex        =   1
         Top             =   240
         Width           =   1815
      End
      Begin VB.Label Label7 
         Caption         =   "EL CLIENTE NO SE ENCUETRA REGISTRADO"
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   8.25
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         ForeColor       =   &H000000FF&
         Height          =   375
         Left            =   3360
         TabIndex        =   12
         Top             =   960
         Width           =   4095
      End
      Begin VB.Label Label2 
         Caption         =   "ID Cliente:"
         Height          =   255
         Index           =   2
         Left            =   360
         TabIndex        =   5
         Top             =   360
         Width           =   1095
      End
      Begin VB.Label Label1 
         Caption         =   "Razon Social:"
         Height          =   375
         Index           =   1
         Left            =   4080
         TabIndex        =   4
         Top             =   360
         Width           =   1215
      End
   End
   Begin VB.Label Label34 
      Caption         =   "28/04/09"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   255
      Left            =   6840
      TabIndex        =   45
      Top             =   120
      Width           =   855
   End
   Begin VB.Label Label33 
      Caption         =   "Fecha De Pedido:"
      Height          =   255
      Left            =   5280
      TabIndex        =   44
      Top             =   120
      Width           =   1335
   End
   Begin VB.Label Label32 
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   255
      Left            =   3000
      TabIndex        =   43
      Top             =   120
      Width           =   615
   End
   Begin VB.Label Label31 
      Caption         =   "Nro Pedido:"
      Height          =   255
      Left            =   1920
      TabIndex        =   42
      Top             =   120
      Width           =   855
   End
   Begin VB.Label Label30 
      Caption         =   "Forma de Pago:"
      Height          =   255
      Left            =   3600
      TabIndex        =   40
      Top             =   7440
      Width           =   1335
   End
   Begin VB.Label Label29 
      Caption         =   "Monto Total:"
      Height          =   255
      Left            =   7560
      TabIndex        =   38
      Top             =   7440
      Width           =   1095
   End
   Begin VB.Label Label28 
      Caption         =   "Cantidad:"
      Height          =   255
      Left            =   3840
      TabIndex        =   35
      Top             =   3960
      Width           =   855
   End
   Begin VB.Label Label27 
      Caption         =   "Tipo Producto:"
      Height          =   255
      Left            =   720
      TabIndex        =   33
      Top             =   3960
      Width           =   1095
   End
End
Attribute VB_Name = "NuevoPedidoMayorista"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
