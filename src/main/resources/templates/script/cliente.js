document.getElementById('botao-cliente').onclick = function () {
    document.getElementById('modal-cliente').style.display = 'block';
    fetchClientes('');
  };

  document.getElementById('voltar-cliente').onclick = function () {
    document.getElementById('modal-cliente').style.display = 'none';
  };
  document.getElementById('search-bar-cliente').onkeyup = function () {
    const searchTerm = this.value.toLowerCase();
    fetchClientes(searchTerm);
  };

  document.getElementById('selecionar-cliente').onclick = function () {
    const selectedClient = document.querySelector('.client-list li.selected');

    if (selectedClient) {
      const nome = selectedClient.textContent.trim();
      const id = selectedClient.getAttribute('data-id');

      document.getElementById('cliente-nome').textContent = 'Cliente: ' + id + " - " + nome;
      document.getElementById('modal-cliente').style.display = 'none';
    } else {
      alert('Selecione um cliente antes de continuar.');
    }
  };

  function fetchClientes(nome) {
    fetch('/clientes?nome=' + nome)
      .then(response => response.json())
      .then(data => {
        const clientList = document.getElementById('client-list');
        clientList.innerHTML = '';
        data.forEach(pessoa => {
          const li = document.createElement('li');
          li.textContent = pessoa.nome;
          li.setAttribute('data-id', pessoa.idPessoa);
          li.addEventListener('click', function () {
            veiculoID = pessoa.veiculos[0].idVeiculo;
            pessoaSelecionada = pessoa;

            if (pessoa.veiculos.length > 0) {
              document.getElementById('veiculo-nome').textContent = 'Veículo: ' + pessoa.veiculos[0].modelo + ' | Cor: ' + pessoa.veiculos[0].cor + ' | Ano: ' + pessoa.veiculos[0].fabricacao;
            } else {
              document.getElementById('veiculo-nome').textContent = 'Nenhum veículo encontrado para este cliente.';
            }

            const selected = document.querySelector('.client-list li.selected');
            if (selected) selected.classList.remove('selected');
            this.classList.add('selected');
          });
          clientList.appendChild(li);
        });
      })
      .catch(error => {
        console.error('Erro ao buscar clientes:', error);
      });
  }
