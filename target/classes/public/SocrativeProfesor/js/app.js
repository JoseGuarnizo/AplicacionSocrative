const app = new Vue({
  el: '#app',
  data: {
    titulo: 'Mi Socrative',
    seeMO: false,
    seeVF: false,
    seeRC: false,
    cuestonario: [],
    pregunta: '',
    resp1: '',
    resp2: '',
    resp3: '',
    rValida: '',
    ws: []
  },
  methods: {
    clear: function() {
      this.pregunta = '';
      this.resp1 = '';
      this.resp2 = '';
      this.resp3 = '';
      this.rValida = '';
    },
    multiOpcion: function() {
      this.cuestonario.push({
        pregunta: this.pregunta,
        opciones: {
          opc1: this.resp1,
          opc2: this.resp2,
          opc3: this.resp3,
        },
        valida: this.rValida
      });
      app.clear();
      localStorage.setItem('data-vue', JSON.stringify(this.cuestonario));
    },
    VFOption: function() {
      this.cuestonario.push({
        pregunta: this.pregunta,
        valida: this.rValida
      });
      app.clear();
      localStorage.setItem('data-vue', JSON.stringify(this.cuestonario));
    },
    RCorta: function() {
      this.cuestonario.push({
        pregunta: this.pregunta,
        valida: this.rValida
      });
      app.clear();
      localStorage.setItem('data-vue', JSON.stringify(this.cuestonario));
    },
    editarTarea: function(index) {
      this.cuestonario[index].estado = true;
      localStorage.setItem('data-vue', JSON.stringify(this.cuestonario));
    },
    eliminarTarea: function(index) {
      this.cuestonario.splice(index, 1);
      localStorage.setItem('data-vue', JSON.stringify(this.cuestonario));
    },
    //metodos de conexion a WebSocket
    connect() {
      socket = new WebSocket("ws://localhost:4567/profesor");
      socket.onopen = this.openWs;
      socket.onerror = this.errorWs;
      //socket.onmessage = this.messageWs;
    },
    openWs() {
      /*this.ws.push({
        estado: "conectado",
        nombre: "Usuario"
      });*/
      //console.log(sw.estado + " " + ws.nombre);
      alert("Usuario conectado");
      //this.sendMessage(this.key);
    },
    errorWs(evt) {
      /*this.ws.push({
        estado: "error",
        nombre: "Usuario"
      });*/
      alert("Usuario fallido");
      console.log(evt.cuestonario);
    },
    messageWs(evt) {
      json = JSON.parse(evt.cuestonario);
      console.log(evt.cuestonario);
    },
    sendMessage() {
      json = JSON.stringify(this.cuestonario);
      socket.send(json);
    }
    /*json = JSON.stringify(evt.cuestonario);
    console.log(evt.cuestonario);
    this.sendMessage(json);*/
  },
  created: function() {
    this.connect();
    let bd = JSON.parse(localStorage.getItem('data-vue'));
    if (bd === null) {
      this.cuestonario = [];
    } else {
      this.cuestonario = bd;
    }
  }
});
