/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import InformacoesComponent from '@/entities/informacoes/informacoes.vue';
import InformacoesClass from '@/entities/informacoes/informacoes.component';
import InformacoesService from '@/entities/informacoes/informacoes.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Informacoes Management Component', () => {
    let wrapper: Wrapper<InformacoesClass>;
    let comp: InformacoesClass;
    let informacoesServiceStub: SinonStubbedInstance<InformacoesService>;

    beforeEach(() => {
      informacoesServiceStub = sinon.createStubInstance<InformacoesService>(InformacoesService);
      informacoesServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<InformacoesClass>(InformacoesComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          informacoesService: () => informacoesServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      informacoesServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllInformacoess();
      await comp.$nextTick();

      // THEN
      expect(informacoesServiceStub.retrieve.called).toBeTruthy();
      expect(comp.informacoes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
