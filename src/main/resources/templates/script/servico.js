

document.getElementById('botao-servico').onclick = function () {
    document.getElementById('modal-servico').style.display = 'block';
  };

  document.getElementById('voltar-servico').onclick = function () {
    document.getElementById('modal-servico').style.display = 'none';
  };

  document.getElementById('search-bar-servico').onkeyup = function () {
    const searchTerm = this.value.toLowerCase();
    fetchServico(searchTerm);
  };

  let servicosSelecionados = [];

  function exibirServicosSelecionados() {
    const servicoNomeDiv = document.getElementById('servico-nome');
    const valorServicoDiv = document.getElementById('valor-servico');
    const tempoServicoDiv = document.getElementById('tempo-servico-horas');

    servicoNomeDiv.innerHTML = '';
    valorServicoDiv.innerHTML = '';
    tempoServicoDiv.innerHTML = '';

    servicosSelecionados.forEach(servico => {
      const servicoNomeSpan = document.createElement('span');
      servicoNomeSpan.textContent = servico.nome;

      const valorServicoSpan = document.createElement('span');
      valorServicoSpan.textContent = formatarValor(parseFloat(servico.valor));

      const tempoServicoSpan = document.createElement('span');
      tempoServicoSpan.textContent = servico.tempo;

      const servicoNomeDivNovo = document.createElement('div');
      servicoNomeDivNovo.classList.add('descricao-servico-item');
      servicoNomeDivNovo.appendChild(servicoNomeSpan);

      const valorServicoDivNovo = document.createElement('div');
      valorServicoDivNovo.classList.add('valor-servico-item');
      valorServicoDivNovo.appendChild(valorServicoSpan);

      const tempoServicoDivNovo = document.createElement('div');
      tempoServicoDivNovo.classList.add('tempo-servico-item');
      tempoServicoDivNovo.appendChild(tempoServicoSpan);

      servicoNomeDiv.appendChild(servicoNomeDivNovo);
      valorServicoDiv.appendChild(valorServicoDivNovo);
      tempoServicoDiv.appendChild(tempoServicoDivNovo);
    });

    const linhaDivisoria = document.createElement('div');
    linhaDivisoria.classList.add('linha-divisoria');
    servicoNomeDiv.appendChild(linhaDivisoria.cloneNode(true));
    valorServicoDiv.appendChild(linhaDivisoria.cloneNode(true));
    tempoServicoDiv.appendChild(linhaDivisoria.cloneNode(true));

    console.log("Serviços Selecionados:");
    servicosSelecionados.forEach(servico => {
      console.log("Nome:", servico.nome, "| Valor:", servico.valor, "| Tempo:", servico.tempo);
    });
  }

  document.getElementById('selecionar-servico').onclick = function () {
    const selectedServico = document.querySelector('.servico-list li.selected');

    if (selectedServico) {
      const nome = selectedServico.textContent.trim();
      const valor = selectedServico.getAttribute('data-valor');
      const tempo = selectedServico.getAttribute('data-tempo');

      servicosSelecionados.push({
        nome: nome,
        valor: valor,
        tempo: tempo
      });

      exibirServicosSelecionados();
      calcularValorTotal();
      document.getElementById('modal-servico').style.display = 'none';
    } else {
      alert('Selecione um serviço antes de continuar.');
    }
  };

  function fetchServico(nome) {
    fetch('/servico?descricao=' + nome)
      .then(response => {
        if (!response.ok) {
          throw new Error('Erro ao buscar serviços');
        }
        return response.json();
      })
      .then(data => {
        const servicoList = document.getElementById('servico-list');
        servicoList.innerHTML = '';
        data.forEach(servico => {
          const li = document.createElement('li');
          li.textContent = servico.descricao;
          tempoServico = servico.tempoServico;
          li.setAttribute('data-id', servico.idServico);
          li.setAttribute('data-valor', servico.valor);
          li.setAttribute('data-tempo', servico.tempoServico);

          li.addEventListener('click', function () {
            horasServico = servico.tempoServico;
            idServico = servico.idServico;
            servicoSelecionado = servico;
            const selected = document.querySelector('.servico-list li.selected');
            if (selected) selected.classList.remove('selected');
            this.classList.add('selected');
          });

          servicoList.appendChild(li);
        });
      })
      .catch(error => {
        console.error('Erro ao buscar serviços:', error);
        alert('Ocorreu um erro ao buscar os serviços. Por favor, tente novamente mais tarde.');
      });
  }