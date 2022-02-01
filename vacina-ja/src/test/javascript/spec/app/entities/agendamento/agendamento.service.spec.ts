/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import AgendamentoService from '@/entities/agendamento/agendamento.service';
import { Agendamento } from '@/shared/model/agendamento.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('Agendamento Service', () => {
    let service: AgendamentoService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new AgendamentoService();
      currentDate = new Date();
      elemDefault = new Agendamento(
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            data: dayjs(currentDate).format(DATE_FORMAT),
            hora: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Agendamento', async () => {
        const returnedFromService = Object.assign(
          {
            nomeCompleto: 'BBBBBB',
            CPF: 'BBBBBB',
            idade: 1,
            sintomas: 'BBBBBB',
            qtdVacinas: 1,
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            endereco: 'BBBBBB',
            complemento: 'BBBBBB',
            CEP: 'BBBBBB',
            nomePosto: 'BBBBBB',
            data: dayjs(currentDate).format(DATE_FORMAT),
            hora: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            data: currentDate,
            hora: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Agendamento', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
