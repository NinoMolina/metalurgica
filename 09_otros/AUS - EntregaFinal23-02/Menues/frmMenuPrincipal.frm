VERSION 5.00
Begin VB.Form frmMenuPrincipal 
   BackColor       =   &H00C0FFFF&
   BorderStyle     =   3  'Fixed Dialog
   Caption         =   "Menu Principal"
   ClientHeight    =   10050
   ClientLeft      =   4950
   ClientTop       =   2055
   ClientWidth     =   12675
   FillColor       =   &H8000000F&
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   10050
   ScaleWidth      =   12675
   ShowInTaskbar   =   0   'False
   StartUpPosition =   1  'CenterOwner
   Begin VB.CommandButton btnCargarDatos 
      BackColor       =   &H0080C0FF&
      Caption         =   "&Carga Datos"
      BeginProperty Font 
         Name            =   "Arial"
         Size            =   20.25
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   -1  'True
         Strikethrough   =   0   'False
      EndProperty
      Height          =   735
      Left            =   3480
      Style           =   1  'Graphical
      TabIndex        =   11
      Top             =   8280
      Width           =   5295
   End
   Begin VB.CommandButton Command2 
      BackColor       =   &H0080C0FF&
      Caption         =   "&Salir"
      BeginProperty Font 
         Name            =   "Arial"
         Size            =   9.75
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   -1  'True
         Strikethrough   =   0   'False
      EndProperty
      Height          =   375
      Left            =   9720
      Style           =   1  'Graphical
      TabIndex        =   8
      Top             =   9600
      Width           =   1815
   End
   Begin VB.PictureBox Picture1 
      BackColor       =   &H00000000&
      Height          =   3495
      Left            =   8760
      Picture         =   "frmMenuPrincipal.frx":0000
      ScaleHeight     =   3435
      ScaleWidth      =   3435
      TabIndex        =   9
      Top             =   120
      Width           =   3495
   End
   Begin VB.Frame Frame2 
      BackColor       =   &H00C0FFFF&
      Caption         =   "Gerente General"
      ForeColor       =   &H00000000&
      Height          =   5775
      Left            =   360
      TabIndex        =   10
      Top             =   3600
      Width           =   11895
      Begin VB.CommandButton Command3 
         BackColor       =   &H0080C0FF&
         Caption         =   "P&roducción"
         BeginProperty Font 
            Name            =   "Arial"
            Size            =   20.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Height          =   735
         Left            =   6120
         Style           =   1  'Graphical
         TabIndex        =   4
         Top             =   840
         Width           =   5295
      End
      Begin VB.CommandButton cmdPedidosMenuPrincipal 
         BackColor       =   &H0080C0FF&
         Caption         =   "&Pedidos"
         BeginProperty Font 
            Name            =   "Arial"
            Size            =   20.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Height          =   735
         Left            =   360
         Style           =   1  'Graphical
         TabIndex        =   3
         Top             =   840
         Width           =   5295
      End
      Begin VB.CommandButton cmdUsuarioMenuPrincipal 
         BackColor       =   &H0080C0FF&
         Caption         =   "Usu&arios"
         BeginProperty Font 
            Name            =   "Arial"
            Size            =   20.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Height          =   735
         Left            =   6120
         MaskColor       =   &H80000013&
         Style           =   1  'Graphical
         TabIndex        =   2
         Top             =   3720
         Width           =   5295
      End
      Begin VB.CommandButton cmdEmpleadosMenuPrincipal 
         BackColor       =   &H0080C0FF&
         Caption         =   "&Empleados"
         BeginProperty Font 
            Name            =   "Arial"
            Size            =   20.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Height          =   735
         Left            =   360
         Style           =   1  'Graphical
         TabIndex        =   5
         Top             =   3720
         Width           =   5295
      End
      Begin VB.CommandButton cmdDistribucionMenuPrincipal 
         BackColor       =   &H0080C0FF&
         Caption         =   "&Distribución"
         BeginProperty Font 
            Name            =   "Arial"
            Size            =   20.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Height          =   735
         Left            =   360
         MaskColor       =   &H80000003&
         Style           =   1  'Graphical
         TabIndex        =   7
         Top             =   1800
         Width           =   5295
      End
      Begin VB.CommandButton cmdProvedoresMenuPrincipal 
         BackColor       =   &H0080C0FF&
         Caption         =   "Pro&veedores"
         BeginProperty Font 
            Name            =   "Arial"
            Size            =   20.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Height          =   735
         Left            =   360
         MaskColor       =   &H00FFFFFF&
         Style           =   1  'Graphical
         TabIndex        =   6
         Top             =   2760
         Width           =   5295
      End
      Begin VB.CommandButton cmdClientesMenuPrincipal 
         BackColor       =   &H0080C0FF&
         Caption         =   "&Clientes"
         BeginProperty Font 
            Name            =   "Arial"
            Size            =   20.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Height          =   735
         Left            =   6120
         MaskColor       =   &H00FFFFFF&
         Style           =   1  'Graphical
         TabIndex        =   1
         Top             =   2760
         Width           =   5295
      End
      Begin VB.CommandButton cmdUnidadDeTransporteMenuPrincipal 
         BackColor       =   &H0080C0FF&
         Caption         =   "&Unidad de Transporte"
         BeginProperty Font 
            Name            =   "Arial"
            Size            =   20.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Height          =   735
         Left            =   6120
         MaskColor       =   &H00FFFFFF&
         Style           =   1  'Graphical
         TabIndex        =   0
         Top             =   1800
         Width           =   5295
      End
   End
   Begin VB.Image Image1 
      Height          =   2445
      Left            =   360
      Picture         =   "frmMenuPrincipal.frx":2544
      Top             =   720
      Width           =   8070
   End
End
Attribute VB_Name = "frmMenuPrincipal"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
    
Private Sub btnCargarDatos_Click()
frmMenuPrincipal.Enabled = False
frmCargaDatos.Show

End Sub

Private Sub cmdClientesMenuPrincipal_Click()

frmMenuClientes.Show
frmMenuPrincipal.Enabled = False

End Sub

Private Sub cmdDistribucionMenuPrincipal_Click()
frmMenuPrincipal.Enabled = False
frmMenuDistribucion.Show
End Sub

Private Sub cmdEmpleadosMenuPrincipal_Click()
frmMenuPrincipal.Enabled = False
frmEmpleado.Show

End Sub

Private Sub cmdPedidosMenuPrincipal_Click()
frmMenuPrincipal.Enabled = False
frmPedidosPrincipal.Show


End Sub

Private Sub cmdProvedoresMenuPrincipal_Click()
frmMenuPrincipal.Enabled = False
frmProveedor.Show
End Sub

Private Sub cmdUnidadDeTransporteMenuPrincipal_Click()
frmMenuPrincipal.Enabled = False
frmMenuUnidadTransporte.Show

End Sub

Private Sub cmdUsuarioMenuPrincipal_Click()

frmUsuario.Show
frmMenuPrincipal.Enabled = False

End Sub

Private Sub cmdZona_Click()
'MsgBox frmLogin.NOMBRE
'MsgBox frmLogin.apellido
'MsgBox frmLogin.cargo
'MsgBox frmLogin.legajo

End Sub

Private Sub Command1_Click()
frmInformesClientes.Show
End Sub

Private Sub Command2_Click()
Unload Me

End Sub

Private Sub Command3_Click()

frmMenuPrincipal.Enabled = False
frmProduccionPrincipal.Show

End Sub

Private Sub Form_Load()
 Module1.conectar
 

Set rslimpiar = New ADODB.Recordset
rslimpiar.CursorLocation = adUseClient
If rslimpiar.State = adStateOpen Then
    rslimpiar.Close
End If

Dim limpiar As String
limpiar = "Select * from nada"
rslimpiar.Open limpiar, Module1.cn, adOpenDynamic, adLockOptimistic
End Sub

Private Sub Form_Unload(Cancel As Integer)
Dim Resp As VbMsgBoxResult
Resp = MsgBox("Desea Cerrar la Aplicación?", vbYesNo + vbQuestion, "Cerrar Programa")

If Resp = vbNo Then
Cancel = 1
Else
End
End If

End Sub

