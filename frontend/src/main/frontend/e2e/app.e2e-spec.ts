import { WorkflowPage } from './app.po';

describe('workflow App', function() {
  let page: WorkflowPage;

  beforeEach(() => {
    page = new WorkflowPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
