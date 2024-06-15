document.getElementById('botao-peca').onclick = function () {
    document.getElementById('modal-peca').style.display = 'block';
  };

  document.getElementById('voltar-peca').onclick = function () {
    document.getElementById('modal-peca').style.display = 'none';
  };

  document.getElementById('search-bar-peca').onkeyup = function () {
    const searchTerm = this.value.toLowerCase();
    fetchMontagem(searchTerm);
  };

  let pecasSelecionadas = [];

  function exibirPecasSelecionadas() {
    const descricaoPecaDiv = document.getElementById('peca-nome');
    const valorPecaDiv = document.getElementById('valor-peca');

    descricaoPecaDiv.innerHTML = '';
    valorPecaDiv.innerHTML = '';

    pecasSelecionadas.forEach(peca => {
      const descricaoPecaSpan = document.createElement('span');
      descricaoPecaSpan.textContent = peca.descricao;

      const valorPecaSpan = document.createElement('span');
      valorPecaSpan.textContent = formatarValor(parseFloat(peca.preco));

      const descricaoPecaDivNova = document.createElement('div');
      descricaoPecaDivNova.classList.add('descricao-peca-item');
      descricaoPecaDivNova.appendChild(descricaoPecaSpan);

      const valorPecaDivNova = document.createElement('div');
      valorPecaDivNova.classList.add('valor-peca-item');
      valorPecaDivNova.appendChild(valorPecaSpan);

      descricaoPecaDiv.appendChild(descricaoPecaDivNova);
      valorPecaDiv.appendChild(valorPecaDivNova);
    });

    const linhaDivisoria = document.createElement('div');
    linhaDivisoria.classList.add('linha-divisoria');
    descricaoPecaDiv.appendChild(linhaDivisoria.cloneNode(true));
    valorPecaDiv.appendChild(linhaDivisoria.cloneNode(true));

    console.log("Peças Selecionadas:");
    pecasSelecionadas.forEach(peca => {
      console.log("Descrição:", peca.descricao, "| Preço:", peca.preco + " | ID: " + peca.idPeca);
    });
  }

  document.getElementById('selecionar-peca').onclick = function () {
    const selectedPeca = document.querySelector('.peca-list li.selected');

    if (selectedPeca) {
      const descricao = selectedPeca.textContent.trim();
      const preco = selectedPeca.getAttribute('data-preco');

      pecasSelecionadas.push({
        descricao: descricao,
        preco: preco
      });

      exibirPecasSelecionadas();
      calcularValorSubTotal();
      calcularValorTotal();
      document.getElementById('modal-peca').style.display = 'none';
    } else {
      alert('Selecione uma peça antes de continuar.');
    }
  };

  function fetchMontagem(nome) {
    fetch('/montagem?descricao=' + nome + '&idVeiculo=' + veiculoID)
      .then(response => {
        if (!response.ok) {
          throw new Error('Erro ao buscar montagem');
        }
        return response.json();
      })
      .then(data => {
        const PecaList = document.getElementById('peca-list');
        PecaList.innerHTML = '';
        data.forEach(montagem => {
          const li = document.createElement('li');
          li.textContent = montagem.peca.descricao;
          li.setAttribute('data-id', montagem.id);
          li.setAttribute('data-preco', montagem.preco);
          li.addEventListener('click', function () {
            idPeca = montagem.idPeca;
            pecaSelecionado = montagem;
            const selected = document.querySelector('.peca-list li.selected');
            if (selected) selected.classList.remove('selected');
            this.classList.add('selected');
          });
          PecaList.appendChild(li);
        });
      })
      .catch(error => {
        console.error('Erro ao buscar peças:', error);
        alert('Ocorreu um erro ao buscar as peças. Por favor, tente novamente mais tarde.');
      });
  }